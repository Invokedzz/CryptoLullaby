package org.cryptolullaby.model.dto;

import jakarta.validation.constraints.Pattern;
import org.cryptolullaby.entity.Interest;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record EditProfileDTO(

        @Length(min =  6, max = 20, message = "Password length must be higher than 6 and lower than 20!")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "Password must contain letters and numbers!")
        String password,

        @Length(min =  6, max = 20, message = "Password length must be higher than 6 and lower than 20!")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "Password must contain letters and numbers!")
        String confirmNewPassword,

        List <Interest> interests

) {}
