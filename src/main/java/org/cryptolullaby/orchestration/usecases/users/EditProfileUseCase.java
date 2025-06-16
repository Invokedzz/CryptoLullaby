package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Images;
import org.cryptolullaby.entity.Interest;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.exception.BadRequestException;
import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.dto.users.ReactivateDTO;
import org.cryptolullaby.service.CloudinaryService;
import org.cryptolullaby.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EditProfileUseCase {

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

    public void reactivateUserAccount (ReactivateDTO reactivateDTO) {

        var user = findUserByEmail(reactivateDTO.email());

        user.activate();

        saveChangesInTheDatabase(user);

    }

    public void deactivateUserAccount (String id) {

        var user = findUserById(id);

        user.deactivate();

        saveChangesInTheDatabase(user);

    }

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

        return usersService.getSanitizedInterestList(interests);

    }

    private Users findUserByEmail (String email) {

        return usersService.findUserByEmail(email);

    }

}
