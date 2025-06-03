package org.cryptolullaby.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.cryptolullaby.entity.Images;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {

    @Value("${default.image.url}")
    private String defaultImgURL;

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {

        this.cloudinary = cloudinary;

    }

    private Map <?, ?> uploadImageToCloud (MultipartFile file) {

        try {

            var convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());

            var fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();

            return cloudinary.uploader().upload(convFile, ObjectUtils.asMap("folder", "/images/"));

        } catch (IOException ex) {

            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, ex.getMessage());

        }

    }

    public void checkImgPropertiesThenSetURL (Images images, MultipartFile file) {

        if (Objects.requireNonNull(file.getContentType()).matches("image/jpeg") || file.getContentType().matches("image/png")) {

            var picture = uploadImageToCloud(file);

            if (picture != null && picture.containsKey("url")) {

                images.setUrl(picture.get("url").toString());

                return;

            }

        }

        images.setUrl(defaultImgURL);

    }

}
