package org.cryptolullaby.orchestration;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.report.CreateReportDTO;
import org.cryptolullaby.infra.email.SendEmailToQueue;
import org.cryptolullaby.model.dto.report.ReportPageDTO;
import org.cryptolullaby.model.dto.report.StoreReportCasesIdDTO;
import org.cryptolullaby.orchestration.usecases.users.ReportUseCase;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportOrchestrationFacade {

    private final ReportUseCase reportUseCase;

    private final SendEmailToQueue sendEmailToQueueUseCase;

    public ReportOrchestrationFacade (ReportUseCase reportUseCase, SendEmailToQueue sendEmailToQueueUseCase) {

        this.reportUseCase = reportUseCase;

        this.sendEmailToQueueUseCase = sendEmailToQueueUseCase;

    }

    public void report (CreateReportDTO createReportDTO) {

        reportUseCase.report(createReportDTO);

    }

    public PagedResponseDTO <ReportPageDTO> getAllEqualsToPendingStatus (Pageable pageable) {

        return reportUseCase.getAllEqualsToPendingStatus(pageable);

    }

    public PagedResponseDTO <ReportPageDTO> getAllEqualsToInAnalysisStatus (Pageable pageable) {

        return reportUseCase.getAllEqualsToInAnalysisStatus(pageable);

    }

    public PagedResponseDTO <ReportPageDTO> getAllEqualsToReportedStatus (Pageable pageable) {

        return reportUseCase.getAllEqualsToReportedStatus(pageable);

    }

    public void confirmReportRequest (StoreReportCasesIdDTO reportCases) {

        reportUseCase.confirmReportRequest(reportCases);

        sendEmailToQueueUseCase.sendConfirmReportEmail(reportCases.email());

    }

    public void denyReportRequest (StoreReportCasesIdDTO reportCases) {

        reportUseCase.denyReportRequest(reportCases);

        sendEmailToQueueUseCase.sendDenyReportEmail(reportCases.email());

    }

    public void randomMethodName () {

        reportUseCase.randomMethodName();

    }

    public Report getReportById (String id) {

        return reportUseCase.getReportById(id);

    }

}
