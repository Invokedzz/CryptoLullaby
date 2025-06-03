package org.cryptolullaby.validation;

import org.cryptolullaby.entity.Users;
import org.cryptolullaby.exception.DivergentPasswordsException;
import org.cryptolullaby.exception.EmailAlreadyExistsException;
import org.cryptolullaby.exception.UsernameAlreadyExistsException;
import org.cryptolullaby.repository.UsersRepository;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private final UsersRepository usersRepository;

    public UserValidator (UsersRepository usersRepository) {

        this.usersRepository = usersRepository;

    }

    public void checkIfUserComponentsAlreadyExist (String username, String email) {

        checkIfUsernameAlreadyExists(username);

        checkIfEmailAlreadyExists(email);

    }

    private void checkIfUsernameAlreadyExists (String username) {

        if (usersRepository.existsByUsername(username)) {

            throw new UsernameAlreadyExistsException("Username already exists!");

        }

    }

    private void checkIfEmailAlreadyExists (String email) {

        if (usersRepository.existsByEmail(email)) {

            throw new EmailAlreadyExistsException("Email already exists!");

        }

    }

}
