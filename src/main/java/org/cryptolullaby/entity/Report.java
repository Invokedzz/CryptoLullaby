package org.cryptolullaby.entity;

import org.cryptolullaby.model.dto.report.CreateReportDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.model.enums.ReportStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("report")
public class Report {

    @Id
    private String id;

    private String reporterId;

    private String reportedId;

    private ReportStatus status;

    private EntityType entityType;

    private LocalDateTime timestamp;

    public Report () {}

    public Report (

            String id, String reporterId, String reportedId,
            ReportStatus status, EntityType entityType, LocalDateTime timestamp

    )

    {

        this.id = id;

        this.reporterId = reporterId;

        this.reportedId = reportedId;

        this.status = status;

        this.entityType = entityType;

        this.timestamp = timestamp;

    }

    public Report (CreateReportDTO createReportDTO, EntityType entityType) {

        this.reporterId = createReportDTO.reporterId();

        this.reportedId = createReportDTO.reportedId();

        this.status = ReportStatus.PENDING;

        this.entityType = entityType;

        this.timestamp = LocalDateTime.now();

    }

    public String getId () {

        return id;

    }

    public String getReporterId () {

        return reporterId;

    }

    public String getReportedId () {

        return reportedId;

    }

    public ReportStatus getStatus () {

        return status;

    }

    public void setStatus (ReportStatus status) {

        this.status = status;

    }

    public EntityType getEntityType () {

        return entityType;

    }

    public void setEntityType (EntityType entityType) {

        this.entityType = entityType;

    }

    public LocalDateTime getTimestamp () {

        return timestamp;

    }

}
