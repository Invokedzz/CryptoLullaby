package org.cryptolullaby.model.dto.posts;

import org.springframework.web.multipart.MultipartFile;

public record EditPostsDTO (

        String title,

        String description,

        MultipartFile img

) {}
