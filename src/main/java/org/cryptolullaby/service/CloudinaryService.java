package org.cryptolullaby.service;

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
import java.util.Set;

@Service
public class CloudinaryService {

    @Value("${default.image.url}")
    private String defaultImgURL;

    private final Cloudinary cloudinary;

    private static final Set <String> ALLOWED_IMAGE_TYPES = Set.of("image/png", "image/jpeg");

    public CloudinaryService(Cloudinary cloudinary) {

        this.cloudinary = cloudinary;

    }

    private Map <?, ?> sendImageToCloud (MultipartFile file) {

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

    public Images renderImage (MultipartFile file, boolean useDefaultImg) {

        if (file.getContentType() != null && ALLOWED_IMAGE_TYPES.contains(file.getContentType())) {

            var picture = sendImageToCloud(file);

            if (picture != null && picture.containsKey("url")) {

                return new Images(picture.get("url").toString());

            }

        }

        if (useDefaultImg) {

            return new Images(defaultImgURL);

        }

        return null;

    }

}
