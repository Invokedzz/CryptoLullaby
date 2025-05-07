package org.cryptolullaby.service;

import org.cryptolullaby.entity.Roles;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.dto.RegisterDTO;
import org.cryptolullaby.model.enums.RolesName;
import org.cryptolullaby.repository.RolesRepository;
import org.cryptolullaby.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    private final RolesService rolesService;

    public UsersService (UsersRepository usersRepository, RolesService rolesService) {

        this.usersRepository = usersRepository;

        this.rolesService = rolesService;

    }

    public void createUser (RegisterDTO register) {

        if (!register.password().equals(register.repeatPassword())) {

        }

        var user = new Users(register);

        List <Roles> roles = List.of(rolesService.createIfRoleNotExistOrElseReturnIt(RolesName.USER));

        user.setRolesId(
                roles
                .stream()
                .map(Roles::getId)
                .collect(Collectors.toList())
        );

        usersRepository.save(user);

    }

}
