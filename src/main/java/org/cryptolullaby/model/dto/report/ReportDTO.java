package org.cryptolullaby.model.dto.report;

import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.model.enums.ReportStatus;

import java.time.LocalDateTime;

public record ReportDTO (

        String reporterId,

        String reportedId,

        ReportStatus status,

        EntityType entityType,

        LocalDateTime timestamp

) {}
