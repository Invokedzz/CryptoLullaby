package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.dto.users.InterestDTO;
import org.cryptolullaby.model.dto.users.RegisterDTO;
import org.cryptolullaby.orchestration.usecases.EditUserUseCase;
import org.cryptolullaby.orchestration.usecases.RegisterUserUseCase;
import org.springframework.stereotype.Service;

@Service
public class UserOrchestrationFacade {

    private final RegisterUserUseCase registerUserUseCase;

    private final EditUserUseCase editUserUseCase;

    public UserOrchestrationFacade (RegisterUserUseCase registerUserUseCase, EditUserUseCase editUserUseCase) {

        this.registerUserUseCase = registerUserUseCase;

        this.editUserUseCase = editUserUseCase;

    }

    public void registerUser (RegisterDTO registerDTO) {

        registerUserUseCase.register(registerDTO);

    }

    public void confirmUserActivation (String id, InterestDTO interestDTO) {}

    public void editUserById (String id, EditProfileDTO profileDTO) {}

    public void deactivateUserById (String id) {}

}
