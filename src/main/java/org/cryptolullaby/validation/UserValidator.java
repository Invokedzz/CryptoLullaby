package org.cryptolullaby.validation;

import org.cryptolullaby.entity.Users;
import org.cryptolullaby.exception.*;
import org.cryptolullaby.repository.UsersRepository;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void validate (String followerId, String followingId) {

        checkIfIdsAreTheSame(followerId, followingId);

        var userList = usersRepository.findAllById(List.of(followerId, followingId));

        var follower = findUserByFollowerIdAndActiveEqualsTrue(userList, followerId);

        var following = findUserByFollowingIdAndActiveEqualsTrue(userList, followingId);

        if (follower != null && following != null) {

            return;

        }

        throw new InvalidFollowInviteException("Something went wrong! Please try again.");

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

    private Users findUserByFollowerIdAndActiveEqualsTrue (List <Users> userList, String followerId) {

        return userList
                .stream()
                .filter(x -> x.getId().equals(followerId) && x.getIsActive().equals(true))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("We weren't able to find a user with this id: " + followerId));

    }

    private Users findUserByFollowingIdAndActiveEqualsTrue (List <Users> userList, String followingId) {

        return userList
                .stream()
                .filter(y -> y.getId().equals(followingId) && y.getIsActive().equals(true))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("We weren't able to find a user with this id: " + followingId));

    }

    private void checkIfIdsAreTheSame (String followerId, String followingId) {

        if (followerId.equals(followingId)) {

            throw new SameIdException("Follower and Following have the same id!");

        }

    }

}
