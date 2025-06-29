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

    private static final String MAIL_TRANSPORT_PROTOCOL = "smtp";

    private static final String IS_PROPERTY_ENABLED = "true";

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

        properties.put("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
        properties.put("mail.smtp.auth", IS_PROPERTY_ENABLED);
        properties.put("mail.smtp.starttls.enable", IS_PROPERTY_ENABLED);
        properties.put("mail.debug", IS_PROPERTY_ENABLED);

    }

}
