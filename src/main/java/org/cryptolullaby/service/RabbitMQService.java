package org.cryptolullaby.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Value("${rabbitmq.email.queue.name}")
    private String emailQueue;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQService (RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;

    }

    public void sendToQueue (String to) {

        rabbitTemplate.convertAndSend(emailQueue, to);

    }

}
