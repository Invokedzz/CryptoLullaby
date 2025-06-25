package org.cryptolullaby.infra.email;

import org.cryptolullaby.model.dto.general.EmailDTO;
import org.cryptolullaby.model.enums.EmailType;
import org.cryptolullaby.service.RabbitMQService;
import org.springframework.stereotype.Service;

@Service
public class SendEmailToQueue {

    private final RabbitMQService rabbitMQService;

    public SendEmailToQueue(RabbitMQService rabbitMQService) {

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

    public void sendConfirmReportEmail (EmailDTO emailDTO) {

        sendReportConfirmEmailToQueue(emailDTO);

    }

    public void sendDenyReportEmail (EmailDTO emailDTO) {

        sendDenyReportEmailToQueue(emailDTO);

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

    private void sendReportConfirmEmailToQueue (EmailDTO emailDTO) {

        rabbitMQService.sendToQueue(emailDTO, EmailType.CONFIRM_REPORT);

    }

    private void sendDenyReportEmailToQueue (EmailDTO emailDTO) {

        rabbitMQService.sendToQueue(emailDTO, EmailType.DENY_REPORT);

    }

}
