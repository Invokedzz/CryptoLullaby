package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class SendEmailUseCase {

    private final EmailService emailService;

    public SendEmailUseCase (EmailService emailService) {

        this.emailService = emailService;

    }

    public void send (String to) {

        sendEmailToUser(to);

    }

    private void sendEmailToUser (String to) {

        emailService.sendEmailToUser(to);

    }

}
