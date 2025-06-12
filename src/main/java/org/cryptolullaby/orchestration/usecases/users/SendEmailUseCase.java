package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.service.EmailService;
import org.cryptolullaby.service.RabbitMQService;
import org.springframework.stereotype.Service;

@Service
public class SendEmailUseCase {

    private final RabbitMQService rabbitMQService;

    public SendEmailUseCase (RabbitMQService rabbitMQService) {

        this.rabbitMQService = rabbitMQService;

    }

    public void send (String to) {

        sendEmailToUser(to);

    }

    private void sendEmailToUser (String to) {

        rabbitMQService.sendToQueue(to);

    }

}
