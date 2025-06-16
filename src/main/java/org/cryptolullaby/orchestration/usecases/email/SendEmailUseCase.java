package org.cryptolullaby.orchestration.usecases.email;

import org.cryptolullaby.entity.Email;
import org.cryptolullaby.exception.BadGatewayException;
import org.cryptolullaby.exception.GatewayTimeoutException;
import org.cryptolullaby.exception.UnprocessableEntityException;
import org.cryptolullaby.model.dto.general.EmailDTO;
import org.cryptolullaby.service.EmailService;
import org.cryptolullaby.util.IEmailQueues;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.*;
import org.springframework.stereotype.Service;

@Service
public class SendEmailUseCase implements IEmailQueues {

    @Value("${spring.mail.username}")
    private String from;

    private final SimpleMailMessage simpleMailMessage;

    private final MailSender mailSender;

    private final EmailService emailService;

    public SendEmailUseCase (SimpleMailMessage simpleMailMessage, MailSender mailSender, EmailService emailService) {

        this.simpleMailMessage = simpleMailMessage;

        this.mailSender = mailSender;

        this.emailService = emailService;

    }

    @Override
    @RabbitListener(queues = "${rabbitmq.register.email.queue}")
    public void sendEmailToUserAfterRegistration (String to) {

        try {

            var email = structureEmail(
                    to, "Registration",
                    "Welcome to CryptoLullaby!");

            var emailDTO = buildEmailDTO(email);

            saveChangesInTheDatabase(new Email(emailDTO));

            mailSender.send(email);

        } catch (MailSendException ex) {

            throw new GatewayTimeoutException(ex.getMessage());

        } catch (MailAuthenticationException ex) {

            throw new BadGatewayException(ex.getMessage());

        } catch (MailParseException ex) {

            throw new UnprocessableEntityException(ex.getMessage());

        }

    }

    @Override
    @RabbitListener(queues = "${rabbitmq.reactivation.email.queue}")
    public void sendEmailToUserAfterAccountReactivation (String to) {

        try {

            var email = structureEmail(
                    to, "Account Reactivation",
                    "We would like to inform that your account has been reactivated!");

            var emailDTO = buildEmailDTO(email);

            saveChangesInTheDatabase(new Email(emailDTO));

            mailSender.send(email);

        } catch (MailSendException ex) {

            throw new GatewayTimeoutException(ex.getMessage());

        } catch (MailAuthenticationException ex) {

            throw new BadGatewayException(ex.getMessage());

        } catch (MailParseException ex) {

            throw new UnprocessableEntityException(ex.getMessage());

        }

    }

    @Override
    @RabbitListener(queues = "${rabbitmq.deactivation.email.queue}")
    public void sendEmailToUserAfterAccountDeactivation (String to) {

        try {

            var email = structureEmail(
                    to, "Account Deactivation",
                    "We would like to inform that your account has been deactivated!");

            var emailDTO = buildEmailDTO(email);

            saveChangesInTheDatabase(new Email(emailDTO));

            mailSender.send(email);

        } catch (MailSendException ex) {

            throw new GatewayTimeoutException(ex.getMessage());

        } catch (MailAuthenticationException ex) {

            throw new BadGatewayException(ex.getMessage());

        } catch (MailParseException ex) {

            throw new UnprocessableEntityException(ex.getMessage());

        }

    }

    private SimpleMailMessage structureEmail (String to, String subject, String text) {

        var structure = simpleMailMessage;

        structure.setTo(to);
        structure.setFrom(from);
        structure.setSubject(subject);
        structure.setText(text);

        return structure;

    }

    private EmailDTO buildEmailDTO (SimpleMailMessage mailMessage) {

        return new EmailDTO(

            mailMessage.getFrom(),
            mailMessage.getTo(),
            mailMessage.getSubject(),
            mailMessage.getText()

        );

    }

    private void saveChangesInTheDatabase (Email email) {

        emailService.save(email);

    }

}
