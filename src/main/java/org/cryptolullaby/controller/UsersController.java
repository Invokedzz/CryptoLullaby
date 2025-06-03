package org.cryptolullaby.controller;

import jakarta.validation.Valid;
import org.cryptolullaby.model.dto.general.ImageDTO;
import org.cryptolullaby.model.dto.general.SystemResponseDTO;
import org.cryptolullaby.model.dto.users.*;
import org.cryptolullaby.orchestration.UserOrchestrationFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domain/users")
public class UsersController {

    private final UserOrchestrationFacade orchestrationFacade;

    public UsersController (UserOrchestrationFacade orchestrationFacade) {

        this.orchestrationFacade = orchestrationFacade;

    }

    @PostMapping("/register")
    public ResponseEntity <SystemResponseDTO> createUser (@Valid @ModelAttribute RegisterDTO register) {

        orchestrationFacade.register(register);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SystemResponseDTO("User created successfully!"));

    }

    @PostMapping("/confirm/activation/{id}")
    public ResponseEntity <SystemResponseDTO> confirmInterestsThenActivateAccount (

            @PathVariable String id,
            @RequestBody InterestDTO interestDTO

    )

    {

        orchestrationFacade.confirmRegistration(id, interestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new SystemResponseDTO("Account activated successfully!"));

    }

    @PostMapping("/login")
    public ResponseEntity <Void> login (@Valid @RequestBody LoginDTO loginDTO) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }

    @GetMapping("/profile/{id}")
    public ResponseEntity <ProfileDTO> findProfileById (@PathVariable String id) {

        var user = orchestrationFacade.getProfile(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @PutMapping("/profile/edit/{id}")
    public ResponseEntity <SystemResponseDTO> editProfileById (

            @PathVariable String id,

            @Valid @ModelAttribute EditProfileDTO profileDTO

    )

    {

        orchestrationFacade.editUserById(id, profileDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new SystemResponseDTO("Profile edited successfully!"));

    }

    @PutMapping("/profile/edit/pfp/{id}")
    public ResponseEntity <Void> editProfileImageById (

            @PathVariable String id,

            @Valid @ModelAttribute ImageDTO imageDTO

    )

    {

        orchestrationFacade.editUserImage(id, imageDTO.file());

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/profile/deactivate/{id}")
    public ResponseEntity <Void> deactivateProfileById (@PathVariable String id) {

        orchestrationFacade.deactivateUserById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
