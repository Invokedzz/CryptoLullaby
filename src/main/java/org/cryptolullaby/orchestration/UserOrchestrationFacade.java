package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.dto.users.InterestDTO;
import org.cryptolullaby.model.dto.users.ProfileDTO;
import org.cryptolullaby.model.dto.users.RegisterDTO;
import org.cryptolullaby.orchestration.usecases.users.ProfileUseCase;
import org.cryptolullaby.orchestration.usecases.users.RegisterUserUseCase;
import org.cryptolullaby.orchestration.usecases.users.SendEmailUseCase;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserOrchestrationFacade {

    private final RegisterUserUseCase registerUserUseCase;

    private final ProfileUseCase profileUseCase;

    private final SendEmailUseCase sendEmailUseCase;

    public UserOrchestrationFacade (RegisterUserUseCase registerUserUseCase, ProfileUseCase profileUseCase, SendEmailUseCase sendEmailUseCase) {

        this.registerUserUseCase = registerUserUseCase;

        this.profileUseCase = profileUseCase;

        this.sendEmailUseCase = sendEmailUseCase;

    }

    public void register (RegisterDTO registerDTO) {

        registerANewUser(registerDTO);

        sendAnEmailAfterRegistration(registerDTO.email());

    }

    public void confirmRegistration (String id, InterestDTO interestDTO) {

        registerUserUseCase.confirmRegistration(id, interestDTO);

    }

    public ProfileDTO getProfile (String id) {

        return profileUseCase.getUserProfileById(id);

    }

    public void editUserById (String id, EditProfileDTO profileDTO) {

        profileUseCase.editUserProfile(id, profileDTO);

    }

    public void editUserImage (String id, MultipartFile file) {

        profileUseCase.editUserImage(id, file);

    }

    public void deactivateUserById (String id) {

        profileUseCase.deactivateUserAccount(id);

    }

    private void registerANewUser (RegisterDTO registerDTO) {

        registerUserUseCase.register(registerDTO);

    }

    private void sendAnEmailAfterRegistration (String email) {

        sendEmailUseCase.send(email);

    }

}
