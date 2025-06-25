package org.cryptolullaby.orchestration.usecases.email;

import org.cryptolullaby.model.dto.general.EmailDTO;
import org.cryptolullaby.model.enums.EmailType;
import org.cryptolullaby.service.RabbitMQService;
import org.springframework.stereotype.Service;

@Service
public class SendEmailToQueueUseCase {

    private final RabbitMQService rabbitMQService;

    public SendEmailToQueueUseCase (RabbitMQService rabbitMQService) {

        this.rabbitMQService = rabbitMQService;

    }

    public void sendRegisterEmail (String to) {

        sendRegisterEmailToQueue(to);

    }

    public void sendReactivationEmail (String to) {

        sendReactivationEmailToQueue(to);

    }

    public void sendDeactivationEmail (String to) {

        sendDeactivationEmailToQueue(to);

    }

    public void sendConfirmReportEmail (String to) {

        sendConfirmReportEmail(to);

    }

    public void sendDenyReportEmail (String to) {

        sendDenyReportEmailToQueue(to);

    }

    private void sendRegisterEmailToQueue (String to) {

        rabbitMQService.sendToQueue(to, EmailType.REGISTRATION);

    }

    private void sendReactivationEmailToQueue (String to) {

        rabbitMQService.sendToQueue(to, EmailType.REACTIVATION);

    }

    private void sendDeactivationEmailToQueue (String to) {

        rabbitMQService.sendToQueue(to, EmailType.DEACTIVATION);

    }

    private void sendReportConfirmEmailToQueue (String to) {

        rabbitMQService.sendToQueue(to, EmailType.CONFIRM_REPORT);

    }

    private void sendDenyReportEmailToQueue (String to) {

        rabbitMQService.sendToQueue(to, EmailType.DENY_REPORT);

    }

}
