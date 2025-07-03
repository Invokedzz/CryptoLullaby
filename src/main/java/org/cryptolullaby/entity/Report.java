package org.cryptolullaby.entity;

import org.bson.types.ObjectId;
import org.cryptolullaby.model.dto.report.CreateReportDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.model.enums.ReportStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("report")
public class Report {

    @Id
    private ObjectId id;

    private String reporterId;

    private String reportedId;

    private String reason;

    private ReportStatus status;

    private EntityType entityType;

    private LocalDateTime timestamp;

    public Report () {}

    public Report (

            ObjectId id, String reporterId, String reportedId, String reason,
            ReportStatus status, EntityType entityType, LocalDateTime timestamp

    )

    {

        this.id = id;

        this.reporterId = reporterId;

        this.reportedId = reportedId;

        this.reason = reason;

        this.status = status;

        this.entityType = entityType;

        this.timestamp = timestamp;

    }

    public Report (CreateReportDTO createReportDTO) {

        this.reporterId = createReportDTO.reporterId();

        this.reportedId = createReportDTO.reportedId();

        this.reason = createReportDTO.reason();

        this.entityType = createReportDTO.type();

        this.status = ReportStatus.PENDING;

        this.timestamp = LocalDateTime.now();

    }

    public ObjectId getId () {

        return id;

    }

    public String getReporterId () {

        return reporterId;

    }

    public String getReportedId () {

        return reportedId;

    }

    public String getReason () {

        return reason;

    }

    public void setReason (String reason) {

        this.reason = reason;

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
