package org.cryptolullaby.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${default.email}")
    private String from;

    private final JavaMailSender mailSender;

    public EmailService (JavaMailSender mailSender) {

        this.mailSender = mailSender;

    }

    @RabbitListener(queues = "${rabbitmq.email.queue.name}")
    public void listen (String to) {

        sendEmailToUser(to);

    }

    public void sendEmailToUser (String to) {

        var message = new SimpleMailMessage();

        message.setFrom(from);
        message.setText("test");
        message.setTo(to);
        message.setSubject("testing aws");

        mailSender.send(message);

    }

}
