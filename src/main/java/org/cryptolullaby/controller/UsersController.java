package org.cryptolullaby.controller;

import jakarta.validation.Valid;
import org.cryptolullaby.model.dto.*;
import org.cryptolullaby.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsersController {

    private final UsersService usersService;

    public UsersController (UsersService usersService) {

        this.usersService = usersService;

    }

    @PostMapping("/register")
    public ResponseEntity <SystemResponseDTO> createUser (@Valid @RequestBody RegisterDTO register) {

        usersService.createUser(register);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SystemResponseDTO("User created successfully!"));

    }

    @PostMapping("/login")
    public ResponseEntity <Void> login (@Valid @RequestBody LoginDTO loginDTO) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }

    @GetMapping("/profile/{id}")
    public ResponseEntity <ProfileDTO> findProfileById (@PathVariable String id) {

        var user = usersService.findProfileById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ProfileDTO(user));

    }

    @PutMapping("/profile/edit/{id}")
    public ResponseEntity <Void> editProfileById (@PathVariable String id, @Valid @RequestBody UpdateProfileDTO profileDTO) {

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/profile/delete/{id}")
    public ResponseEntity <Void> deleteProfileById (@PathVariable String id) {

        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
