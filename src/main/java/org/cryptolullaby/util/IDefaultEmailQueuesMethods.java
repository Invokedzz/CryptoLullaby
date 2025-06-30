package org.cryptolullaby.util;

public interface IDefaultEmailQueuesMethods {

    void sendEmailToUserAfterRegistration (String to);

    void sendEmailToUserAfterAccountReactivation (String to);

    void sendEmailToUserAfterAccountDeactivation (String to);

}
