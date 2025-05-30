package org.cryptolullaby.service;

import org.cryptolullaby.entity.Interest;
import org.cryptolullaby.entity.Roles;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.exception.UserNotFoundException;
import org.cryptolullaby.model.dto.users.InterestDTO;
import org.cryptolullaby.model.dto.users.RegisterDTO;
import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.enums.InterestName;
import org.cryptolullaby.model.enums.RolesName;
import org.cryptolullaby.repository.UsersRepository;
import org.cryptolullaby.validation.UserValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    private final RolesService rolesService;

    private final PasswordEncoder passwordEncoder;

    private final UserValidator userValidator;

    private final CloudinaryService cloudinaryService;

    public UsersService (UsersRepository usersRepository, RolesService rolesService,
                         PasswordEncoder passwordEncoder, UserValidator userValidator,
                        CloudinaryService cloudinaryService) {

        this.usersRepository = usersRepository;

        this.rolesService = rolesService;

        this.passwordEncoder = passwordEncoder;

        this.userValidator = userValidator;

        this.cloudinaryService = cloudinaryService;

    }

    /*
    *
    *  Public methods (usually connected to controller)
    *
    *
    * */

    public void createUser (RegisterDTO register) {

        checkIfPasswordsMatch(register.password(), register.repeatPassword());

        var user = new Users(register);

        checkIfUserComponentsAlreadyExist(user);

        String encodedPassword = encodeUserPassword(register.password());

        user.setPassword(encodedPassword);

        setupUserRoles(user);

        cloudinaryService.checkImgPropertiesThenSetURL(user.getImg(), register.img());

        usersRepository.save(user);

    }

    public void confirmProfileActivation (String id, InterestDTO interestDTO) {

        var user = findUserById(id);

        if (interestDTO.interests() != null) {

            var sanitizedList = sanitizeInterestList(interestDTO.interests());

            user.setInterests(sanitizedList);

            user.activate();

            usersRepository.save(user);

        }

    }

    public Users findProfileById (String id) {

        return findUserById(id);

    }

    public void editProfileById (String id, EditProfileDTO profileDTO) {

        var user = findUserById(id);

        var sanitizedList = sanitizeInterestList(profileDTO.interests());

        var profile = new EditProfileDTO(

                profileDTO.password(),
                profileDTO.confirmNewPassword(),
                sanitizedList

        );

        checkIfNewPasswordIsDifferentFromTheOlderOne(user.getPassword(), profile.password());

        checkIfPasswordsMatch(profile.password(), profile.confirmNewPassword());

        user.editProfile(profile);

        usersRepository.save(user);

    }

    public void editProfileImageById (String id, MultipartFile file) {

        var user = findUserById(id);

        cloudinaryService.checkImgPropertiesThenSetURL(user.getImg(), file);

        usersRepository.save(user);

    }

    public void deactivateProfileById (@PathVariable String id) {

        var user = findUserById(id);

        user.deactivate();

        usersRepository.save(user);

    }

    /*
    *
    *  Private methods
    *
    * */

    private Users findUserById (String id) {

        return usersRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

    }

    private List <Interest> sanitizeInterestList (List <Interest> interests) {

        /*
        *
        *  To do: fix the NullPointerIssue that is happening with interests.stream()
        *   09/05/2025
        *
        *   FIXED 12/05/2025
        *
        * */

        if (interests != null) {

            var sanitizedList =
                    interests.stream()
                            .filter(i -> i.getType() != null && !i.getType().getLabel().isBlank())
                            .collect(Collectors.toList());

            if (sanitizedList.isEmpty()) {

                sanitizedList.add(new Interest(InterestName.NONE));

            }

            return sanitizedList;

        }

        return List.of(new Interest(InterestName.NONE));

    }

    private String encodeUserPassword (String password) {

        return passwordEncoder.encode(password);

    }

    private void setupUserRoles (Users user) {

        List <Roles> roles = List.of(rolesService.createIfRoleNotExistOrElseReturnIt(RolesName.USER));

        user.setRolesId(
                roles
                        .stream()
                        .map(Roles::getId)
                        .collect(Collectors.toList())
        );

    }

    private void checkIfUserComponentsAlreadyExist (Users user) {

        userValidator.checkIfUserComponentsAlreadyExist(user);

    }

    private void checkIfPasswordsMatch (String password, String confirmPassword) {

        userValidator.checkIfPasswordsAreTheSame(password, confirmPassword);

    }

    private void checkIfNewPasswordIsDifferentFromTheOlderOne (String oldPassword, String newPassword) {

        userValidator.comparePasswordsInOrderToEditAccount(oldPassword, newPassword);

    }

}
