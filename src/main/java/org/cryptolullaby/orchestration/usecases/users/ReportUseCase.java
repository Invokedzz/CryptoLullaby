package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.service.ReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportUseCase {

    private final ReportService reportService;

    public ReportUseCase (ReportService reportService) {

        this.reportService = reportService;

    }

    public void report () {}

    public void getAllReportsMadeToAnEntity () {}

    public void getAllReportsMadeToAnEntityById () {}

    public void getReportById () {}

    public void deleteReportByIdAndEntity () {}

}
