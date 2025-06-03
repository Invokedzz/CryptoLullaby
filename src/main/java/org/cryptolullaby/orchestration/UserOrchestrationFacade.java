package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.dto.users.InterestDTO;
import org.cryptolullaby.model.dto.users.RegisterDTO;
import org.cryptolullaby.orchestration.usecases.EditUserUseCase;
import org.cryptolullaby.orchestration.usecases.RegisterUserUseCase;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserOrchestrationFacade {

    private final RegisterUserUseCase registerUserUseCase;

    private final EditUserUseCase editUserUseCase;

    public UserOrchestrationFacade (RegisterUserUseCase registerUserUseCase, EditUserUseCase editUserUseCase) {

        this.registerUserUseCase = registerUserUseCase;

        this.editUserUseCase = editUserUseCase;

    }

    public void register (RegisterDTO registerDTO) {

        registerUserUseCase.register(registerDTO);

    }

    public void confirmRegistration (String id, InterestDTO interestDTO) {

        registerUserUseCase.confirmRegistration(id, interestDTO);

    }

    public void editUserById (String id, EditProfileDTO profileDTO) {

        editUserUseCase.editUserProfile(id, profileDTO);

    }

    public void editUserImage (String id, MultipartFile file) {

        editUserUseCase.editUserImage(id, file);

    }

    public void deactivateUserById (String id) {

        editUserUseCase.deactivateUserAccount(id);

    }

}
