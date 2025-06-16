package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.dto.users.InterestDTO;
import org.cryptolullaby.model.dto.users.ProfileDTO;
import org.cryptolullaby.model.dto.users.RegisterDTO;
import org.cryptolullaby.orchestration.usecases.users.EditProfileUseCase;
import org.cryptolullaby.orchestration.usecases.users.ProfileUseCase;
import org.cryptolullaby.orchestration.usecases.users.RegisterUserUseCase;
import org.cryptolullaby.orchestration.usecases.users.SendEmailUseCase;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserOrchestrationFacade {

    private final RegisterUserUseCase registerUserUseCase;

    private final ProfileUseCase profileUseCase;

    private final SendEmailUseCase sendEmailUseCase;

    private final EditProfileUseCase editProfileUseCase;

    public UserOrchestrationFacade (RegisterUserUseCase registerUserUseCase, ProfileUseCase profileUseCase, SendEmailUseCase sendEmailUseCase, EditProfileUseCase editProfileUseCase) {

        this.registerUserUseCase = registerUserUseCase;

        this.profileUseCase = profileUseCase;

        this.sendEmailUseCase = sendEmailUseCase;

        this.editProfileUseCase = editProfileUseCase;

    }

    public void register (RegisterDTO registerDTO) {

        registerANewUser(registerDTO);

     //   sendAnEmailAfterRegistration(registerDTO.email());

    }

    public void confirmRegistration (String id, InterestDTO interestDTO) {

        registerUserUseCase.confirmRegistration(id, interestDTO);

    }

    public PagedResponseDTO<ProfileDTO> getProfile (String id, Pageable pageable) {

        return profileUseCase.getUserProfileById(id, pageable);

    }

    public void editUserById (String id, EditProfileDTO profileDTO) {

        editProfileUseCase.editUserProfile(id, profileDTO);

    }

    public void editUserImage (String id, MultipartFile file) {

        editProfileUseCase.editUserImage(id, file);

    }

    public void deactivateUserById (String id) {

        editProfileUseCase.deactivateUserAccount(id);

    }

    private void registerANewUser (RegisterDTO registerDTO) {

        registerUserUseCase.register(registerDTO);

    }

   /* private void sendAnEmailAfterRegistration (String email) {

        sendEmailUseCase.send(email);

    } */

}
