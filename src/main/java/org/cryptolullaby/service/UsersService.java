package org.cryptolullaby.service;

import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.dto.users.InterestDTO;
import org.cryptolullaby.model.dto.users.RegisterDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UsersService {

    void createUser (RegisterDTO register);

    void confirmProfileActivation (String id, InterestDTO interestDTO);

    Users findProfileById (String id);

    void editProfileById (String id, EditProfileDTO profileDTO);

    void editProfileImageById (String id, MultipartFile file);

    void deactivateProfileById (String id);

}
