package org.cryptolullaby.model.dto.report;

import jakarta.validation.constraints.NotNull;
import org.cryptolullaby.model.enums.EntityType;

public record CreateReportDTO (

        @NotNull(message = "Reporter Id cannot be null!")
        String reporterId,

        @NotNull(message = "Reported Id cannot be null!")
        String reportedId,

        String reason,

        EntityType type

) {}
