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

    @PostMapping("/confirm/activation")
    public ResponseEntity <Void> confirmInterestsThenActivateAccount () {

        return ResponseEntity.status(HttpStatus.OK).build();

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
    public ResponseEntity <SystemResponseDTO> editProfileById (@PathVariable String id, @Valid @RequestBody EditProfileDTO profileDTO) {

        usersService.editProfileById(id, profileDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new SystemResponseDTO("Profile edited successfully!"));

    }

    @DeleteMapping("/profile/deactivate/{id}")
    public ResponseEntity <Void> deactivateProfileById (@PathVariable String id) {

        usersService.deactivateProfileById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
