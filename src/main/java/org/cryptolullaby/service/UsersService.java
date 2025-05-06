package org.cryptolullaby.service;

import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.dto.RegisterDTO;
import org.cryptolullaby.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService (UsersRepository usersRepository) {

        this.usersRepository = usersRepository;

    }

    public void createUser (RegisterDTO register) {

        if (!register.password().equals(register.repeatPassword())) {

        }

        var user = new Users(register);

        usersRepository.save(user);

    }

}
