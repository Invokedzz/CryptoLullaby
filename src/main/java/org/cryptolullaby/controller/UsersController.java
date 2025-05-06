package org.cryptolullaby.controller;

import org.cryptolullaby.service.UsersService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    private final UsersService usersService;

    public UsersController (UsersService usersService) {

        this.usersService = usersService;

    }

}
