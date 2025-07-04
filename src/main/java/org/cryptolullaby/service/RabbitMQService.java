package org.cryptolullaby.service;

import org.cryptolullaby.exception.BadRequestException;
import org.cryptolullaby.model.dto.general.EmailDTO;
import org.cryptolullaby.model.enums.EmailType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RabbitMQService {

    @Value("${rabbitmq.register.email.queue}")
    private String registerEmailQueue;

    @Value("${rabbitmq.reactivation.email.queue}")
    private String reactivationEmailQueue;

    @Value("${rabbitmq.deactivation.email.queue}")
    private String deactivationEmailQueue;

    @Value("${rabbitmq.confirm.report.email.queue}")
    private String confirmReportQueue;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQService (RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;

    }

    public void sendToQueue (String to, EmailType emailType) {

        switch (emailType) {

            case REGISTRATION -> rabbitTemplate.convertAndSend(registerEmailQueue, to);

            case REACTIVATION -> rabbitTemplate.convertAndSend(reactivationEmailQueue, to);

            case DEACTIVATION -> rabbitTemplate.convertAndSend(deactivationEmailQueue, to);

            default -> throw new BadRequestException("Invalid email type!");

        };

    }

    public void sendToQueue (EmailDTO emailDTO, EmailType emailType) {

        if (Objects.requireNonNull(emailType) == EmailType.REPORT) {

            rabbitTemplate.convertAndSend(confirmReportQueue, emailDTO);

            return;

        }

        throw new BadRequestException("Invalid email type!");

    }

}
