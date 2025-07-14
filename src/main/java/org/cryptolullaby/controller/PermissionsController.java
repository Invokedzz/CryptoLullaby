package org.cryptolullaby.controller;

import org.cryptolullaby.orchestration.PermissionsOrchestrationFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain/permissions")
public class PermissionsController {

    private final PermissionsOrchestrationFacade orchestrationFacade;

    public PermissionsController (PermissionsOrchestrationFacade orchestrationFacade) {

        this.orchestrationFacade = orchestrationFacade;

    }

    @PutMapping("/{id}")
    public ResponseEntity <Void> addRoleById (@PathVariable String id) {

        orchestrationFacade.addACertainRoleToAnUserById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
