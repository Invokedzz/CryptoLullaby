package org.cryptolullaby.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.tika.Tika;
import org.cryptolullaby.entity.Images;
import org.cryptolullaby.exception.DirectoryTraversalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
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

            // we need to fix this garbage -> garbage apparently fixed :p

            var convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());

            failIfDirectoryTraversal(convFile);

            var fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();

            return cloudinary.uploader().upload(convFile, ObjectUtils.asMap("folder", "/images/"));

        } catch (IOException ex) {

            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, ex.getMessage());

        }

    }

    public Images renderImage (MultipartFile file, boolean useDefaultImg) {

        if (file.getContentType() != null && isFileValid(file)) {

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

    private boolean isFileValid (MultipartFile file) {

        var tika = new Tika();

        String type = tika.detect(file.getContentType());

        return ALLOWED_IMAGE_TYPES.contains(type);

    }

    private void failIfDirectoryTraversal (File file) {

        if (file.isAbsolute()) {

            throw new RuntimeException("Directory traversal attempt - absolute path not allowed!");

        }

        String pathUsingCanonical, pathUsingAbsolute;

        try {

            pathUsingCanonical = file.getCanonicalPath();
            pathUsingAbsolute = file.getAbsolutePath();

        } catch (IOException e) {

            throw new DirectoryTraversalException(e.getMessage());

        }

        // Require the absolute path and canonicalized path match.
        // This is done to avoid directory traversal
        // attacks, e.g. "1/../2/"
        if (!pathUsingCanonical.equals(pathUsingAbsolute)) {

            throw new DirectoryTraversalException("Directory traversal attempt?");

        }

    }

}
