package org.cryptolullaby.util;

public interface IDefaultEmailQueues {

    void sendEmailToUserAfterRegistration (String to);

    void sendEmailToUserAfterAccountReactivation (String to);

    void sendEmailToUserAfterAccountDeactivation (String to);

}
