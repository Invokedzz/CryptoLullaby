package org.cryptolullaby.util;

public interface IEmailQueues {

    void sendEmailToUserAfterRegistration (String to);

    void sendEmailToUserAfterAccountReactivation (String to);

    void sendEmailToUserAfterAccountDeactivation (String to);

}
