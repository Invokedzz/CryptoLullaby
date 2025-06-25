package org.cryptolullaby.model.dto.report;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.validation.annotations.BlockHTML;

public record CreateReportDTO (

        @NotBlank(message = "Reporter Id cannot be blank!")
        String reporterId,

        @NotBlank(message = "Reported Id cannot be blank!")
        String reportedId,

        @BlockHTML
        @NotNull(message = "Reason field cannot be null!")
        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]*$", message = "Reason field must contain letters and numbers!")
        String reason,

        @NotNull(message = "Type cannot be null!")
        EntityType type

) {}
