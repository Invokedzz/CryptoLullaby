package org.cryptolullaby.util;

import org.cryptolullaby.model.dto.general.EmailDTO;
import org.springframework.mail.SimpleMailMessage;

public interface IDTOEmailQueuesMethods {

    void sendEmailAfterReportCaseHasBeenDecided (EmailDTO emailDTO);

    default EmailDTO buildEmailDTO (SimpleMailMessage mailMessage) {

        return new EmailDTO(

                mailMessage.getFrom(),
                mailMessage.getTo(),
                mailMessage.getSubject(),
                mailMessage.getText()

        );

    }

}
