package org.cryptolullaby.util;

import org.cryptolullaby.model.dto.general.EmailDTO;

public interface IEmailQueues {

    void sendEmailToUserAfterRegistration (String to);

    void sendEmailToUserAfterAccountReactivation (String to);

    void sendEmailToUserAfterAccountDeactivation (String to);

    void sendEmailAfterReportConfirmation (EmailDTO emailDTO);

    void sendEmailAfterDenyReport (EmailDTO emailDTO);

}
