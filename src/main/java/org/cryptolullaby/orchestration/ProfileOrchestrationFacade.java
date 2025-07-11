package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.dto.users.ProfileDTO;
import org.cryptolullaby.orchestration.usecases.users.EditProfileUseCase;
import org.cryptolullaby.orchestration.usecases.users.ProfileUseCase;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileOrchestrationFacade {

    private final ProfileUseCase profileUseCase;

    private final EditProfileUseCase editProfileUseCase;

    public ProfileOrchestrationFacade (ProfileUseCase profileUseCase, EditProfileUseCase editProfileUseCase) {

        this.profileUseCase = profileUseCase;

        this.editProfileUseCase = editProfileUseCase;

    }

    public PagedResponseDTO<ProfileDTO> getProfile (String id, Pageable pageable) {

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

    public void changeProfileVisibilityById (String id) {

        editProfileUseCase.changeProfileVisibilityById(id);

    }

}
