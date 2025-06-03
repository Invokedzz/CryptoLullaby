package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Images;
import org.cryptolullaby.entity.Interest;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.dto.users.ProfileDTO;
import org.cryptolullaby.service.CloudinaryService;
import org.cryptolullaby.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProfileUseCase {

    private final UsersService usersService;

    private final CloudinaryService cloudinaryService;

    public ProfileUseCase (UsersService usersService, CloudinaryService cloudinaryService) {

        this.usersService = usersService;

        this.cloudinaryService = cloudinaryService;

    }

    public ProfileDTO getUserProfileById (String id) {

        var user = findUserById(id);

        return new ProfileDTO(user);

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

        setupProfileImage(user.getImg(), file);

        saveChangesInTheDatabase(user);

    }

    public void deactivateUserAccount (String id) {

        var user = findUserById(id);

        user.deactivate();

        saveChangesInTheDatabase(user);

    }

    private void setupProfileImage (Images images, MultipartFile file) {

        cloudinaryService.renderImage(images, file);

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

    private List <Interest> sanitizeInterests (List <Interest> interests) {

        return usersService.getSanitizedInterestList(interests);

    }

}
