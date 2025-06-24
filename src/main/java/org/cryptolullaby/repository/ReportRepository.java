package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.model.enums.EntityType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository <Report, String> {

    void deleteByIdAndEntityType (String id, EntityType entityType);

    boolean existsByReporterIdAndReportedIdAndEntityType (String reporterId, String reportedId, EntityType entityType);

    long countReportByIdAndEntityType (String id, EntityType entityType);

}
