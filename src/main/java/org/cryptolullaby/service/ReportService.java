package org.cryptolullaby.service;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.repository.ReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService (ReportRepository reportRepository) {

        this.reportRepository = reportRepository;

    }

    public void save (Report report) {}

    public void countNumberOfReports () {}

    public void hasUserReported () {}

}
