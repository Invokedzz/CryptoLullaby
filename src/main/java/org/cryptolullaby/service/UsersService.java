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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    private final UserValidator userValidator;

    public UsersService (UsersRepository usersRepository, UserValidator userValidator) {

        this.usersRepository = usersRepository;

        this.userValidator = userValidator;

    }

    public void save (Users user) {

        theseComponentsAreValidOrNot(user.getUsername(), user.getEmail());

        usersRepository.save(user);

    }

    public Users findUserById (String id) {

        return usersRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("We couldn't find a user with id: " + id));

    }

    public Users findUserByEmail (String email) {

        return usersRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("We weren't able to find a user with this email: " + email));

    }

    private void theseComponentsAreValidOrNot (String username, String email) {

        userValidator.checkIfUserComponentsAlreadyExist(username, email);

    }

}
