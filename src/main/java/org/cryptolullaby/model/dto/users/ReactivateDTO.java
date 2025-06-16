package org.cryptolullaby.model.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ReactivateDTO (

        @NotNull(message = "Email field cannot be null!")
        @Email(message = "Please, enter a valid email!")
        String email

) {}
