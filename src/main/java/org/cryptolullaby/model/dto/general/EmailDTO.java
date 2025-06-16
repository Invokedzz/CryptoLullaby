package org.cryptolullaby.model.dto.general;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EmailDTO (

        String from,

        String[] to,

        String subject,

        String content

) {}
