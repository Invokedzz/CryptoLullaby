package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.general.EmailDTO;
import org.cryptolullaby.model.dto.report.CreateReportDTO;
import org.cryptolullaby.orchestration.usecases.email.SendEmailToQueueUseCase;
import org.cryptolullaby.orchestration.usecases.users.ReportUseCase;
import org.springframework.stereotype.Service;

@Service
public class ReportOrchestrationFacade {

    private final ReportUseCase reportUseCase;

    private final SendEmailToQueueUseCase sendEmailToQueueUseCase;

    public ReportOrchestrationFacade (ReportUseCase reportUseCase, SendEmailToQueueUseCase sendEmailToQueueUseCase) {

        this.reportUseCase = reportUseCase;

        this.sendEmailToQueueUseCase = sendEmailToQueueUseCase;

    }

    public void report (CreateReportDTO createReportDTO) {

        reportUseCase.report(createReportDTO);

    }

    public void getAllEqualsToPendingStatusAndReporterId () {}

    public void getAllEqualsToReportedStatusAndReporterId () {}

    public void confirmReportRequest (EmailDTO emailDTO) {

        sendEmailToQueueUseCase.sendConfirmReportEmail(emailDTO);

    }

    public void denyReportRequest (EmailDTO emailDTO) {

        sendEmailToQueueUseCase.sendDenyReportEmail(emailDTO);

    }

    public void getReportById () {}

}
