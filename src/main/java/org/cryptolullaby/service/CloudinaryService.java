package org.cryptolullaby.service;

import org.cryptolullaby.entity.Images;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    void checkImgPropertiesThenSetURL (Images images, MultipartFile file);

}
