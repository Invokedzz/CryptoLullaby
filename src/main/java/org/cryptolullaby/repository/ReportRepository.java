package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository <Report, String> {
}
