package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.model.enums.ReportStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository <Report, String> {

    Page <Report> findAllByStatus(ReportStatus status, Pageable pageable);

    boolean existsByReporterIdAndReportedIdAndEntityType (String reporterId, String reportedId, EntityType entityType);

    long countReportByIdAndEntityType (String id, EntityType entityType);

    long countReportByReportedIdAndStatus(String reportedId, ReportStatus status);

    void deleteByReporterIdAndEntityType(String reporterId, EntityType entityType);
}
