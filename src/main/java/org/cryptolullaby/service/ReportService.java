package org.cryptolullaby.service;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.exception.ReportNotFoundException;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.model.enums.ReportStatus;
import org.cryptolullaby.repository.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService (ReportRepository reportRepository) {

        this.reportRepository = reportRepository;

    }

    public void save (Report report) {

        reportRepository.save(report);

    }

    public Report findReportById (String id) {

        return reportRepository
                .findById(id)
                .orElseThrow(() -> new ReportNotFoundException("Report not found!"));

    }

    public Page <Report> findAllByStatusEqualsToPendingAndReporterId (String reporterId, Pageable pageable) {

        return reportRepository.findAllByReporterIdAndStatus(
                reporterId, ReportStatus.PENDING, pageable
        );

    }

    public Page <Report> findAllByStatusEqualsToPendingAndReportedId (String reportedId, Pageable pageable) {

        return reportRepository.findAllByReportedIdAndStatus(
                reportedId, ReportStatus.PENDING, pageable
        );

    }

    public Page <Report> findAllByStatusEqualsToReportedAndReporterId (String reporterId, Pageable pageable) {

        return reportRepository.findAllByReporterIdAndStatus(
                reporterId, ReportStatus.REPORTED, pageable
        );

    }

    public Page <Report> findAllByStatusEqualsToReportedAndReportedId (String reportedId, Pageable pageable) {

        return reportRepository.findAllByReportedIdAndStatus(
                reportedId, ReportStatus.REPORTED, pageable
        );

    }

    public void deleteReportByIdAndEntity (String id, EntityType entityType) {

        reportRepository.deleteByIdAndEntityType(id, entityType);

    }

    public long countNumberOfReports (String id, EntityType entityType) {

        return reportRepository.countReportByIdAndEntityType(id, entityType);

    }

    public boolean hasUserReported (String reporterId, String reportedId, EntityType entityType) {

        return reportRepository.existsByReporterIdAndReportedIdAndEntityType(
                reporterId, reportedId, entityType
        );

    }

}
