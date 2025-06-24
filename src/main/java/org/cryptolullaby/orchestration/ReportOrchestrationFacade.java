package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.report.CreateReportDTO;
import org.cryptolullaby.orchestration.usecases.users.ReportUseCase;
import org.springframework.stereotype.Service;

@Service
public class ReportOrchestrationFacade {

    private final ReportUseCase reportUseCase;

    public ReportOrchestrationFacade (ReportUseCase reportUseCase) {

        this.reportUseCase = reportUseCase;

    }

    public void report (CreateReportDTO createReportDTO) {

        reportUseCase.report(createReportDTO);

    }

    public void getAllReportsMadeToAnEntity () {}

    public void getAllReportsMadeToAnEntityById () {}

    public void getReportById () {}

    public void deleteReportByIdAndEntity () {}

}
