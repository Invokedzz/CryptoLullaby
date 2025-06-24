package org.cryptolullaby.model.dto.report;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.model.enums.ReportStatus;

import java.time.LocalDateTime;

public record ReportDTO (

        String reporterId,

        String reportedId,

        ReportStatus status,

        EntityType entityType,

        LocalDateTime timestamp

)

{

    public ReportDTO (Report report) {

        this (
                report.getReporterId(), report.getReportedId(), report.getStatus(),
                report.getEntityType(), report.getTimestamp()

        );

    }

}
