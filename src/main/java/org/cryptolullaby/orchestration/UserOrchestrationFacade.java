package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.dto.users.InterestDTO;
import org.cryptolullaby.model.dto.users.ProfileDTO;
import org.cryptolullaby.model.dto.users.RegisterDTO;
import org.cryptolullaby.orchestration.usecases.users.ProfileUseCase;
import org.cryptolullaby.orchestration.usecases.users.RegisterUserUseCase;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserOrchestrationFacade {

    private final RegisterUserUseCase registerUserUseCase;

    private final ProfileUseCase profileUseCase;

    public UserOrchestrationFacade (RegisterUserUseCase registerUserUseCase, ProfileUseCase profileUseCase) {

        this.registerUserUseCase = registerUserUseCase;

        this.profileUseCase = profileUseCase;

    }

    public void register (RegisterDTO registerDTO) {

        registerUserUseCase.register(registerDTO);

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

}
