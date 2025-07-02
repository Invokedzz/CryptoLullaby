package org.cryptolullaby.service;

import org.cryptolullaby.entity.Users;
import org.cryptolullaby.exception.EmailNotFoundException;
import org.cryptolullaby.exception.UserNotFoundException;
import org.cryptolullaby.repository.UsersRepository;
import org.cryptolullaby.validation.UserValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    private final UserValidator userValidator;

    private static final boolean IS_ACTIVE = true;

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

    public Page <Users> findByIdAndIsActive (String id, Pageable pageable) {

        return usersRepository.findByIdAndIsActive(
                id, IS_ACTIVE, pageable
        );

    }

    public Users findUserById (String id) {

        return usersRepository.findById(id).orElse(null);

    }

    public Users findUserByIdOrElseThrow (String id) {

        return usersRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("We couldn't find a user with id: " + id));

    }

    public Page <Users> findProfileByUsername (String username, Pageable pageable) {

        return usersRepository.findByUsernameAndIsActive(
                username, IS_ACTIVE, pageable
        );

    }

    public boolean doesUserExist (String id) {

        return usersRepository.existsByIdAndIsActive(
                id, IS_ACTIVE
        );

    }

    public void checkIfBothIdsAreValid (String firstUserId, String secondUserId) {

        userValidator.validate(firstUserId, secondUserId);

    }

    public Users findUserByEmailAndIsActive (String email, boolean isActive) {

        return usersRepository
                .findByEmailAndIsActive(email, isActive)
                .orElseThrow(() -> new EmailNotFoundException("We couldn't find a user with email: " + email));

    }

    private void theseComponentsAreValidOrNot (String username, String email) {

        userValidator.checkIfUserComponentsAlreadyExist(username, email);

    }

}
