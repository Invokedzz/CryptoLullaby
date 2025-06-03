package org.cryptolullaby.orchestration.usecases;

import org.cryptolullaby.entity.Images;
import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.service.CloudinaryService;
import org.cryptolullaby.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EditUserUseCase {

    private final UsersService usersService;

    private final CloudinaryService cloudinaryService;

    public EditUserUseCase (UsersService usersService, CloudinaryService cloudinaryService) {

        this.usersService = usersService;

        this.cloudinaryService = cloudinaryService;

    }

    public void editUserProfile (String id, EditProfileDTO profileDTO) {

        var user = usersService.findUserById(id);

        var interests = usersService.getSanitizedInterestList(profileDTO.interests());

        var editedProfile = new EditProfileDTO(

                profileDTO.password(),
                interests

        );

        user.editProfile(editedProfile);

        usersService.save(user);

    }

    public void editUserImage (String id, MultipartFile file) {

        var user = usersService.findUserById(id);

        setupProfileImage(user.getImg(), file);

        usersService.save(user);

    }

    public void deactivateUserAccount (String id) {

        var user = usersService.findUserById(id);

        user.deactivate();

        usersService.save(user);

    }

    private void setupProfileImage (Images images, MultipartFile file) {

        cloudinaryService.renderImage(images, file);

    }

}
