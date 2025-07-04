package org.cryptolullaby.infra.email;

import org.cryptolullaby.model.dto.general.EmailDTO;
import org.cryptolullaby.model.enums.EmailType;
import org.cryptolullaby.service.RabbitMQService;
import org.springframework.stereotype.Service;

@Service
public class SendEmailToQueue {

    private final RabbitMQService rabbitMQService;

    private static final EmailType REGISTRATION_EMAIL_TYPE = EmailType.REGISTRATION;

    private static final EmailType REACTIVATION_EMAIL_TYPE = EmailType.REACTIVATION;

    private static final EmailType DEACTIVATION_EMAIL_TYPE = EmailType.DEACTIVATION;

    private static final EmailType REPORT_EMAIL_TYPE = EmailType.REPORT;

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

    private void sendRegisterEmailToQueue (String to) {

        rabbitMQService.sendToQueue(to, REGISTRATION_EMAIL_TYPE);

    }

    private void sendReactivationEmailToQueue (String to) {

        rabbitMQService.sendToQueue(to, REACTIVATION_EMAIL_TYPE);

    }

    private void sendDeactivationEmailToQueue (String to) {

        rabbitMQService.sendToQueue(to, DEACTIVATION_EMAIL_TYPE);

    }

    private void sendReportConfirmEmailToQueue (EmailDTO emailDTO) {

        rabbitMQService.sendToQueue(emailDTO, REPORT_EMAIL_TYPE);

    }

}
