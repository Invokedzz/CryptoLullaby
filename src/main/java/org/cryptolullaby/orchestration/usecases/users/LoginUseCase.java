package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.service.KeycloakService;
import org.cryptolullaby.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private final UsersService usersService;

    private final KeycloakService keycloakService;

    public LoginUseCase (UsersService usersService, KeycloakService keycloakService) {

        this.usersService = usersService;

        this.keycloakService = keycloakService;

    }

}
