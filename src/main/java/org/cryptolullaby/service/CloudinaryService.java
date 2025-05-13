package org.cryptolullaby.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService (Cloudinary cloudinary) {

        this.cloudinary = cloudinary;

    }

    public Map <?, ?> uploadImageToCloud (MultipartFile file) {

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

}
