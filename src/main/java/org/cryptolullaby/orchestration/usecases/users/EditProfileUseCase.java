package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Images;
import org.cryptolullaby.entity.Interest;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.dto.users.EmailResponseDTO;
import org.cryptolullaby.model.enums.ProfileStatus;
import org.cryptolullaby.service.CloudinaryService;
import org.cryptolullaby.service.UsersService;
import org.cryptolullaby.util.IUserInterestSanitizer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EditProfileUseCase implements IUserInterestSanitizer {

    private final UsersService usersService;

    private final CloudinaryService cloudinaryService;

    private static final boolean DEFAULT_IMAGE_ICON = true;

    public EditProfileUseCase (UsersService usersService, CloudinaryService cloudinaryService) {

        this.usersService = usersService;

        this.cloudinaryService = cloudinaryService;

    }


    public void editUserProfile (String id, EditProfileDTO profileDTO) {

        var user = findUserById(id);

        var interests = sanitizeInterests(profileDTO.interests());

        var editedProfile = new EditProfileDTO(

                profileDTO.password(),
                interests

        );

        setupComponentsForProfileEdit(user, editedProfile);

        saveChangesInTheDatabase(user);

    }

    public void editUserImage (String id, MultipartFile file) {

        var user = findUserById(id);

        var img = setupProfileImage(file);

        user.setImgUrl(img);

        saveChangesInTheDatabase(user);

    }

    public void reactivateUserAccount (EmailResponseDTO emailResponseDTO) {

        var user = findUserByEmail(
                emailResponseDTO.email(),
                false
        );

        user.activate();

        saveChangesInTheDatabase(user);

    }

    public void deactivateUserAccount (EmailResponseDTO emailResponseDTO) {

        var user = findUserByEmail(
                emailResponseDTO.email(),
                true
        );

        user.deactivate();

        saveChangesInTheDatabase(user);

    }

    public void changeProfileVisibilityById (String id) {

        var user = findUserById(id);

        if (user.getStatus().getFirst().equals(ProfileStatus.PUBLIC)) {

            user.getStatus().removeFirst();

            user.getStatus().addFirst(ProfileStatus.PRIVATE);

        } else if (user.getStatus().getFirst().equals(ProfileStatus.PRIVATE)) {

            user.getStatus().removeFirst();

            user.getStatus().addFirst(ProfileStatus.PUBLIC);

        }

        saveChangesInTheDatabase(user);

    }

    /*

        PRIVATE METHODS AHEAD!

    */

    private Images setupProfileImage (MultipartFile file) {

        return cloudinaryService.renderImage(file, DEFAULT_IMAGE_ICON);

    }

    private Users findUserById (String id) {

        return usersService.findUserById(id);

    }

    private void saveChangesInTheDatabase (Users user) {

        usersService.save(user);

    }

    private void setupComponentsForProfileEdit (Users user, EditProfileDTO profileDTO) {

        user.editProfile(profileDTO);

    }

    private List<Interest> sanitizeInterests (List <Interest> interests) {

        return getSanitizedInterestList(interests);

    }

    private Users findUserByEmail (String email, boolean isActive) {

        return usersService.findUserByEmailAndIsActive(email, isActive);

    }

}
