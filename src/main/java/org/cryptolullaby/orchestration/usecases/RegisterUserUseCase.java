package org.cryptolullaby.orchestration.usecases;

import org.cryptolullaby.entity.Images;
import org.cryptolullaby.entity.Interest;
import org.cryptolullaby.entity.Roles;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.dto.users.InterestDTO;
import org.cryptolullaby.model.dto.users.RegisterDTO;
import org.cryptolullaby.model.enums.InterestName;
import org.cryptolullaby.service.CloudinaryService;
import org.cryptolullaby.service.PasswordService;
import org.cryptolullaby.service.RolesService;
import org.cryptolullaby.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RegisterUserUseCase {

    private final UsersService usersService;

    private final RolesService rolesService;

    private final PasswordService passwordService;

    private final CloudinaryService cloudinaryService;

    public RegisterUserUseCase (UsersService usersService, RolesService rolesService, PasswordService passwordService, CloudinaryService cloudinaryService) {

        this.usersService = usersService;

        this.rolesService = rolesService;

        this.passwordService = passwordService;

        this.cloudinaryService = cloudinaryService;

    }

    public void register (RegisterDTO registerDTO) {

        var user = new Users(registerDTO);

        var encodedPassword = encodeUserPassword(user.getPassword());

        var theyMatch = checkIfThePasswordsMatch(registerDTO.repeatPassword(), encodedPassword);

        if (theyMatch) {

            user.setPassword(encodedPassword);

            setRoleIdInUserAccount(user);

            setupProfileImage(user.getImg(), registerDTO.img());

            usersService.save(user);

        }

    }

    public void confirmRegistration (String id, InterestDTO interestDTO) {

        var user = usersService.findUserById(id);

        if (interestDTO.interests() != null) {

            var interests = usersService.getSanitizedInterestList(interestDTO.interests());

            user.setInterests(interests);

            user.activate();

            usersService.save(user);

        }

    }

    private String encodeUserPassword (String password) {

        return passwordService.encode(password);

    }

    private boolean checkIfThePasswordsMatch (String rawPassword, String encodedPassword) {

        return passwordService.matches(rawPassword, encodedPassword);

    }

    private void setupProfileImage (Images images, MultipartFile file) {

        cloudinaryService.renderImage(images, file);

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

}
