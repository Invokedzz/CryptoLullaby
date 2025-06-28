package org.cryptolullaby.orchestration;

import org.cryptolullaby.entity.Report;
import org.cryptolullaby.model.dto.general.EmailDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.report.CreateReportDTO;
import org.cryptolullaby.infra.email.SendEmailToQueue;
import org.cryptolullaby.model.dto.report.ReportDTO;
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

    public PagedResponseDTO <ReportDTO> getAllEqualsToPendingStatusAndReporterId (String reporterId, Pageable pageable) {

        return reportUseCase.getAllEqualsToPendingStatusAndReporterId(
                reporterId, pageable
        );

    }

    public PagedResponseDTO <ReportDTO> getAllEqualsToReportedStatusAndReporterId (String reporterId, Pageable pageable) {

        return reportUseCase.getAllEqualsToReportedStatusAndReporterId(
                reporterId, pageable
        );

    }

    public void confirmReportRequest (EmailDTO emailDTO) {

        reportUseCase.confirmReportRequest(emailDTO);

        sendEmailToQueueUseCase.sendConfirmReportEmail(emailDTO);

    }

    public void denyReportRequest (EmailDTO emailDTO) {

        reportUseCase.denyReportRequest(emailDTO);

        sendEmailToQueueUseCase.sendDenyReportEmail(emailDTO);

    }

    public Report getReportById (String id) {

        return reportUseCase.getReportById(id);

    }

}
