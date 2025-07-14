package org.cryptolullaby.controller;

import jakarta.validation.Valid;
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

    @PutMapping("/reactivate")
    public ResponseEntity <Void> reactivateProfileByEmail (@Valid @RequestBody EmailResponseDTO emailResponseDTO) {

        orchestrationFacade.reactivateUserByEmail(emailResponseDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/deactivate")
    public ResponseEntity <Void> deactivateProfileById (@Valid @RequestBody EmailResponseDTO emailResponseDTO) {

        orchestrationFacade.deactivateUserById(emailResponseDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
