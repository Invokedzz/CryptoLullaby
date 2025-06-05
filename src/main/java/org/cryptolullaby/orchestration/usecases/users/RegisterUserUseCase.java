package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.entity.Images;
import org.cryptolullaby.entity.Interest;
import org.cryptolullaby.entity.Roles;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.dto.users.InterestDTO;
import org.cryptolullaby.model.dto.users.RegisterDTO;
import org.cryptolullaby.service.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RegisterUserUseCase {

    private final UsersService usersService;

    private final RolesService rolesService;

    private final PasswordService passwordService;

    private final CloudinaryService cloudinaryService;

    private final EmailService emailService;

    private static final boolean DEFAULT_IMAGE_ICON = true;

    public RegisterUserUseCase (UsersService usersService, RolesService rolesService, PasswordService passwordService, CloudinaryService cloudinaryService, EmailService emailService) {

        this.usersService = usersService;

        this.rolesService = rolesService;

        this.passwordService = passwordService;

        this.cloudinaryService = cloudinaryService;

        this.emailService = emailService;

    }

    public void register (RegisterDTO registerDTO) {

        var user = new Users(registerDTO);

        var encodedPassword = encodeUserPassword(user.getPassword());

        var theyMatch = checkIfThePasswordsMatch(registerDTO.repeatPassword(), encodedPassword);

        if (theyMatch) {

            user.setPassword(encodedPassword);

            setRoleIdInUserAccount(user);

            var img = setupProfileImage(registerDTO.img());

            user.setImgUrl(img);

            saveChangesInTheDatabase(user);

        }

    }

    public void confirmRegistration (String id, InterestDTO interestDTO) {

        var user = findUserById(id);

        if (interestDTO.interests() != null) {

            var interests = sanitizeInterests(interestDTO.interests());

            user.setInterests(interests);

            user.activate();

            saveChangesInTheDatabase(user);

        }

    }

    private String encodeUserPassword (String password) {

        return passwordService.encode(password);

    }

    private boolean checkIfThePasswordsMatch (String rawPassword, String encodedPassword) {

        return passwordService.matches(rawPassword, encodedPassword);

    }

    private Images setupProfileImage (MultipartFile file) {

        return cloudinaryService.renderImage(file, DEFAULT_IMAGE_ICON);

    }

    private void saveChangesInTheDatabase (Users user) {

        usersService.save(user);

    }

    private List <Interest> sanitizeInterests (List <Interest> interests) {

        return usersService.getSanitizedInterestList(interests);

    }

    private void setRoleIdInUserAccount (Users user) {

        var roles = List.of(rolesService.getDefaultRole());

        user.setRolesId (

                roles
                        .stream()
                        .map(Roles::getId)
                        .toList()

        );

    }

    private Users findUserById (String id) {

        return usersService.findUserById(id);

    }

}
