package org.cryptolullaby.service;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.exception.ReportNotFoundException;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.model.enums.ReportStatus;
import org.cryptolullaby.repository.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional <Report> findReportOptionalById (String id) {

        return reportRepository.findById(id);

    }

    public Page <Report> findAllByStatusEqualsToPending (Pageable pageable) {

        return reportRepository.findAllByStatus(
                ReportStatus.PENDING, pageable
        );

    }

    public Page <Report> findAllByStatusEqualsToInAnalysis (Pageable pageable) {

        return reportRepository.findAllByStatus(
                ReportStatus.IN_ANALYSIS, pageable
        );

    }

    public Page <Report> findAllByStatusEqualsToReported (Pageable pageable) {

        return reportRepository.findAllByStatus(
                ReportStatus.REPORTED, pageable
        );

    }

    public void deleteReportByIdAndEntity (String id, EntityType entityType) {

        reportRepository.deleteByIdAndEntityType(id, entityType);

    }

    public long countNumberOfReports (String id, EntityType entityType) {

        return reportRepository.countReportByIdAndEntityType(id, entityType);

    }

    public long countNumberOfTimesAUserHasBeenReported (String reportedId, ReportStatus reportStatus) {

        return reportRepository.countReportByReportedIdAndStatus(
                reportedId, reportStatus
        );

    }

    public boolean hasUserReported (String reporterId, String reportedId, EntityType entityType) {

        return reportRepository.existsByReporterIdAndReportedIdAndEntityType(
                reporterId, reportedId, entityType
        );

    }

}
