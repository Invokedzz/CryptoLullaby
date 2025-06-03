package org.cryptolullaby.orchestration.usecases;

import org.cryptolullaby.service.UsersService;
import org.cryptolullaby.validation.UserValidator;
import org.springframework.stereotype.Service;

@Service
public class EditUserUseCase {

    private final UsersService usersService;

    private final UserValidator userValidator;

    public EditUserUseCase (UsersService usersService, UserValidator userValidator) {

        this.usersService = usersService;

        this.userValidator = userValidator;

    }

}
