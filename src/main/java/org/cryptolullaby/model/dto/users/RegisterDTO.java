package org.cryptolullaby.model.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.cryptolullaby.validation.annotations.BlockHTML;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public record RegisterDTO (

        /*
        *
        * To do: add a regex pattern to passwords, example: passwords must contain letters and numbers -> Done
        * To do: App must accept only JPG, JPEG and PNG as valid files for images -> Done
        *
        * 09/05/2025
        *
        * */

        @BlockHTML
        @NotBlank(message = "Username field cannot be blank!")
        @Length(min =  3, max = 40, message = "Username length must be higher than 3 and lower than 40!")
        String username,

        @Email(message = "Please, enter a valid email!")
        String email,

        @NotBlank(message = "Password field cannot be blank!")
        @Length(min =  6, max = 20, message = "Password length must be higher than 6 and lower than 20!")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "Password must contain letters and numbers!")
        String password,

        @NotBlank(message = "Repeat Password field cannot be blank!")
        @Length(min =  6, max = 20, message = "Password length must be higher than 6 and lower than 20!")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "Password must contain letters and numbers!")
        String repeatPassword,

        @NotNull(message = "Image field cannot be null!")
        MultipartFile img

) {}
