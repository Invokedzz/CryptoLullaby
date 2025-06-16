package org.cryptolullaby.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailSenderConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    private static final int PORT = 587;

    @Bean
    public JavaMailSender getMailSender() {

        var mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(PORT);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        configureMailProperties(mailSender);

        return mailSender;

    }

    @Bean
    public SimpleMailMessage getSimpleMailMessage () {

        return new SimpleMailMessage();

    }

    private void configureMailProperties (JavaMailSenderImpl mailSender) {

        var properties = mailSender.getJavaMailProperties();

        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");

    }

}
