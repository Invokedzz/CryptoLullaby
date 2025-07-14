package org.cryptolullaby.orchestration.usecases.users;

import org.cryptolullaby.service.RolesService;
import org.cryptolullaby.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class PermissionsUseCase {

    private final UsersService usersService;

    private final RolesService rolesService;

    public PermissionsUseCase (UsersService usersService, RolesService rolesService) {

        this.usersService = usersService;

        this.rolesService = rolesService;

    }

    public void addACertainRoleToAnUserById (String id) {}

}
