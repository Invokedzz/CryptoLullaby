package org.cryptolullaby.model.dto.report;

import jakarta.validation.constraints.NotNull;

public record CreateReportDTO (

        @NotNull(message = "Reporter Id cannot be null!")
        String reporterId,

        @NotNull(message = "Reported Id cannot be null!")
        String reportedId

) {}
