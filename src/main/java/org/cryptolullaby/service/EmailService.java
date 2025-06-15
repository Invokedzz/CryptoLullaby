package org.cryptolullaby.service;

import org.cryptolullaby.repository.EmailRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;

    private final EmailRepository emailRepository;

    public EmailService (JavaMailSender mailSender, EmailRepository emailRepository) {

        this.mailSender = mailSender;

        this.emailRepository = emailRepository;

    }

    public void sendRegisterEmailToUser (SimpleMailMessage message) {

        mailSender.send(message);

    }



}
