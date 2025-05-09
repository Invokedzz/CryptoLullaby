package org.cryptolullaby.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.cryptolullaby.entity.Images;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public record RegisterDTO (

        @NotBlank
        @Length(min =  3, max = 40, message = "Username length must be higher than 3 and lower than 40!")
        String username,

        @Email(message = "Please, enter a valid email!")
        String email,

        @NotBlank
        @Length(min =  6, max = 20, message = "Password length must be higher than 6 and lower than 20!")
        String password,

        @NotBlank
        @Length(min =  6, max = 20, message = "Password length must be higher than 6 and lower than 20!")
        String repeatPassword,

        MultipartFile img

) {}
