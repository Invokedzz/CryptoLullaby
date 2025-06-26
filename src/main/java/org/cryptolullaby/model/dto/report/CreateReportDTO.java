package org.cryptolullaby.model.dto.report;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.validation.annotations.BlockHTML;

public record CreateReportDTO (

        @NotBlank(message = "Reporter Id cannot be blank!")
        @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "ReporterId has an invalid ObjectId format!")
        String reporterId,

        @NotNull(message = "Reported Id cannot be blank!")
        @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "ReportedId has an invalid ObjectId format!")
        String reportedId,

        @BlockHTML
        @NotBlank(message = "Reason field cannot be null!")
        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]*$", message = "Reason field must contain letters and numbers!")
        String reason,

        @NotNull(message = "Type cannot be blank!")
        EntityType type

) {}
