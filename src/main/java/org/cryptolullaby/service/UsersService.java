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
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    public void saveWithValidation (Users user) {

        theseComponentsAreValidOrNot(user.getUsername(), user.getEmail());

        usersRepository.save(user);

    }

    public void save (Users user) {

        usersRepository.save(user);

    }

    public Users findUserById (String id) {

        return usersRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("We couldn't find a user with id: " + id));

    }

    public void checkIfBothIdsAreValid (String followerId, String followingId) {

        userValidator.validate(followerId, followingId);

    }

    public Users findUserByEmail (String email) {

        return usersRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("We weren't able to find a user with this email: " + email));

    }

    public List <Interest> getSanitizedInterestList (List <Interest> interests) {

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

    private void theseComponentsAreValidOrNot (String username, String email) {

        userValidator.checkIfUserComponentsAlreadyExist(username, email);

    }

}
