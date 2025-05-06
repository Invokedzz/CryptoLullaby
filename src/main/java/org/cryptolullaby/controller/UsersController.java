package org.cryptolullaby.controller;

import jakarta.validation.Valid;
import org.cryptolullaby.model.dto.RegisterDTO;
import org.cryptolullaby.model.dto.SystemResponseDTO;
import org.cryptolullaby.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    private final UsersService usersService;

    public UsersController (UsersService usersService) {

        this.usersService = usersService;

    }

    @PostMapping
    public ResponseEntity <SystemResponseDTO> createUser (@Valid @RequestBody RegisterDTO register) {

        usersService.createUser(register);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SystemResponseDTO("User created successfully!"));

    }

}
