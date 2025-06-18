package org.cryptolullaby.model.dto.posts;

import jakarta.validation.constraints.Pattern;
import org.cryptolullaby.validation.annotations.BlockHTML;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public record EditPostsDTO (

        @BlockHTML
        @Length(min = 3, max = 50, message = "Title field must have 3 to 50 characters!")
        @Pattern(regexp = "^(?!\\s*$).+", message = "Title field must contain numbers, letters or special characters!")
        String title,

        @BlockHTML
        @Length(max = 1024, message = "Description field cannot exceed 1024 characters!")
        @Pattern(regexp = "^(?!\\s*$).+", message = "Description field must contain numbers, letters or special characters!")
        String description,

        MultipartFile img

) {}
