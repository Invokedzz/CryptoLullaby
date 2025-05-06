package org.cryptolullaby.service;

import org.cryptolullaby.entity.Users;
import org.cryptolullaby.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService (UsersRepository usersRepository) {

        this.usersRepository = usersRepository;

    }

}
