package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.users.*;
import org.cryptolullaby.infra.email.SendEmailToQueue;
import org.cryptolullaby.orchestration.usecases.users.EditProfileUseCase;
import org.cryptolullaby.orchestration.usecases.users.ProfileUseCase;
import org.cryptolullaby.orchestration.usecases.users.RegisterUserUseCase;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserOrchestrationFacade {

    private final RegisterUserUseCase registerUserUseCase;

    private final ProfileUseCase profileUseCase;

    private final SendEmailToQueue sendToQueueUseCase;

    private final EditProfileUseCase editProfileUseCase;

    public UserOrchestrationFacade (RegisterUserUseCase registerUserUseCase, ProfileUseCase profileUseCase, SendEmailToQueue sendToQueueUseCase, EditProfileUseCase editProfileUseCase) {

        this.registerUserUseCase = registerUserUseCase;

        this.profileUseCase = profileUseCase;

        this.sendToQueueUseCase = sendToQueueUseCase;

        this.editProfileUseCase = editProfileUseCase;

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

    public PagedResponseDTO <ProfileDTO> getProfile (String id, Pageable pageable) {

        return profileUseCase.getUserProfileById(id, pageable);

    }

    public PagedResponseDTO <ProfileDTO> getProfileByUsername (String username, Pageable pageable) {

        return profileUseCase.getProfileByUsername(username, pageable);

    }

    public void editUserById (String id, EditProfileDTO profileDTO) {

        editProfileUseCase.editUserProfile(id, profileDTO);

    }

    public void editUserImage (String id, MultipartFile file) {

        editProfileUseCase.editUserImage(id, file);

    }

    public void reactivateUserByEmail (EmailResponseDTO reactivateDTO) {

        editProfileUseCase.reactivateUserAccount(reactivateDTO);

        sendReactivationEmail(reactivateDTO.email());

    }

    public void deactivateUserById (EmailResponseDTO emailResponseDTO) {

        editProfileUseCase.deactivateUserAccount(emailResponseDTO);

        sendDeactivationEmail(emailResponseDTO.email());

    }

    public void changeProfileVisibilityById (String id) {

        editProfileUseCase.changeProfileVisibilityById(id);

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
