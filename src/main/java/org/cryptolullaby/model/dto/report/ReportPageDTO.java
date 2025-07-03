package org.cryptolullaby.model.dto.report;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.model.dto.users.UsernameEmailDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.model.enums.ReportStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ReportPageDTO(

        String reporterId,

        String reportedId,

        List <UsernameEmailDTO> users,

        ReportStatus status,

        EntityType entityType,

        LocalDateTime timestamp

)

{

    public ReportPageDTO(Report report, List <UsernameEmailDTO> users) {

        this (
                report.getReporterId(), report.getReportedId(), users, report.getStatus(),
                report.getEntityType(), report.getTimestamp()

        );

    }

}
