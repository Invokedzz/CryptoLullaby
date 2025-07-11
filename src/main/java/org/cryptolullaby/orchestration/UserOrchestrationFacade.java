package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.users.*;
import org.cryptolullaby.infra.email.SendEmailToQueue;
import org.cryptolullaby.orchestration.usecases.users.DeactivateReactivateAccountUseCase;
import org.cryptolullaby.orchestration.usecases.users.RegisterUserUseCase;
import org.springframework.stereotype.Service;

@Service
public class UserOrchestrationFacade {

    private final RegisterUserUseCase registerUserUseCase;

    private final SendEmailToQueue sendToQueueUseCase;

    private final DeactivateReactivateAccountUseCase deactivateReactivateAccountUseCase;

    public UserOrchestrationFacade (RegisterUserUseCase registerUserUseCase, SendEmailToQueue sendToQueueUseCase, DeactivateReactivateAccountUseCase deactivateReactivateAccountUseCase) {

        this.registerUserUseCase = registerUserUseCase;

        this.sendToQueueUseCase = sendToQueueUseCase;

        this.deactivateReactivateAccountUseCase = deactivateReactivateAccountUseCase;

    }

    /*

        PUBLIC METHODS FOR CONTROLLERS AHEAD!

    */

    public void register (RegisterDTO registerDTO) {

        registerANewUser(registerDTO);

        sendRegisterEmail(registerDTO.email());

    }

    public void confirmRegistration (String id, InterestDTO interestDTO) {

        registerUserUseCase.confirmRegistration(id, interestDTO);

    }

    public void reactivateUserByEmail (EmailResponseDTO reactivateDTO) {

        deactivateReactivateAccountUseCase.reactivateUserAccount(reactivateDTO);

        sendReactivationEmail(reactivateDTO.email());

    }

    public void deactivateUserById (EmailResponseDTO emailResponseDTO) {

        deactivateReactivateAccountUseCase.deactivateUserAccount(emailResponseDTO);

        sendDeactivationEmail(emailResponseDTO.email());

    }

    /*

        PRIVATE METHODS AHEAD!

    */

    private void registerANewUser (RegisterDTO registerDTO) {

        registerUserUseCase.register(registerDTO);

    }

    private void sendRegisterEmail (String to) {

        sendToQueueUseCase.sendRegisterEmail(to);

    }

    private void sendReactivationEmail (String to) {

        sendToQueueUseCase.sendReactivationEmail(to);

    }

    private void sendDeactivationEmail (String to) {

        sendToQueueUseCase.sendDeactivationEmail(to);

    }

}
