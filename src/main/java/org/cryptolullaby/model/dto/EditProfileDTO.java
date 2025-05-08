package org.cryptolullaby.model.dto;

import jakarta.validation.constraints.Email;
import org.cryptolullaby.entity.Interest;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record EditProfileDTO(

        @Length(min =  6, max = 20, message = "Password length must be higher than 6 and lower than 20!")
        String password,

        @Length(min =  6, max = 20, message = "Password length must be higher than 6 and lower than 20!")
        String confirmNewPassword,

        List <Interest> interests

) {}
