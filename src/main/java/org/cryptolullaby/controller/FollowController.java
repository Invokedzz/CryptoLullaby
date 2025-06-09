package org.cryptolullaby.controller;

import org.cryptolullaby.orchestration.FollowOrchestrationFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domain/follow")
public class FollowController {

    private final FollowOrchestrationFacade orchestrationFacade;

    public FollowController (FollowOrchestrationFacade orchestrationFacade) {

        this.orchestrationFacade = orchestrationFacade;

    }

    @GetMapping("/{id}/followers")
    public ResponseEntity <Void> allOfUsersFollowers (@PathVariable String id) {

        orchestrationFacade.getAllOfUsersFollowers();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/{id}/requests")
    public ResponseEntity <Void> allFollowRequestsSentToACertainUser (@PathVariable String id) {

        orchestrationFacade.getAllFollowRequestsSentToACertainUser();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/{id}/myrequests")
    public ResponseEntity <Void> allFollowRequestsMadeByACertainUser (@PathVariable String id) {

        orchestrationFacade.getAllFollowRequestsMadeByACertainUser();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/{id}/blocked")
    public ResponseEntity <Void> allBlockedUsersBySomeCertainUser (@PathVariable String id) {

        orchestrationFacade.getAllBlockedUsersBySomeCertainUser();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity <Void> follow (@PathVariable String id) {

        orchestrationFacade.follow();

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}/accept")
    public ResponseEntity <Void> acceptFollowRequest (@PathVariable String id) {

        orchestrationFacade.acceptFollowRequest();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{id}/reject")
    public ResponseEntity <Void> rejectFollowRequest (@PathVariable String id) {

        orchestrationFacade.rejectFollowRequest();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{id}/block")
    public ResponseEntity <Void> block (@PathVariable String id) {

        orchestrationFacade.block();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
