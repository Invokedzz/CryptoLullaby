package org.cryptolullaby.model.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public record CreatePostDTO (

        /*
        *
        * To do: use token to catch users id, don't rely on a mere dto!
        *
        * 10/05/2025
        *
        * */

        @NotBlank(message = "Title field cannot be blank!")
        @Length(min = 3, max = 50, message = "Title field must have 3 to 50 characters!")
        String title,

        String description,

        MultipartFile img,

        @NotBlank(message = "User Id field cannot be blank!")
        String userId

) {}
