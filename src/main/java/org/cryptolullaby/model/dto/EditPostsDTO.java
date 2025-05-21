package org.cryptolullaby.model.dto;

import org.springframework.web.multipart.MultipartFile;

public record EditPostsDTO (

        String title,

        String description,

        MultipartFile img

) {}
