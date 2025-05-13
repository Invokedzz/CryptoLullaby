package org.cryptolullaby.model.dto;

import org.springframework.web.multipart.MultipartFile;

public record EditPostsDTO (

        MultipartFile img,

        String title,

        String description

) {}
