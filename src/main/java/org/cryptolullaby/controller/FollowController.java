package org.cryptolullaby.controller;

import org.cryptolullaby.model.dto.follow.FollowDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
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
    public ResponseEntity <PagedResponseDTO<FollowDTO>> allOfUsersFollowers (@PathVariable String id) {

        var allFollowers = orchestrationFacade.getAllOfUsersFollowers();

        return ResponseEntity.status(HttpStatus.OK).body(allFollowers);

    }

    @GetMapping("/{id}/requests")
    public ResponseEntity <PagedResponseDTO<FollowDTO>> allFollowRequestsSentToACertainUser (@PathVariable String id) {

        var allFollowRequests = orchestrationFacade.getAllFollowRequestsSentToACertainUser();

        return ResponseEntity.status(HttpStatus.OK).body(allFollowRequests);

    }

    @GetMapping("/{id}/myrequests")
    public ResponseEntity <PagedResponseDTO<FollowDTO>> allFollowRequestsMadeByACertainUser (@PathVariable String id) {

        var allFollowRequests = orchestrationFacade.getAllFollowRequestsMadeByACertainUser();

        return ResponseEntity.status(HttpStatus.OK).body(allFollowRequests);

    }

    @GetMapping("/{id}/blocked")
    public ResponseEntity <PagedResponseDTO<FollowDTO>> allBlockedUsersBySomeCertainUser (@PathVariable String id) {

        var allBlockedUsers = orchestrationFacade.getAllBlockedUsersBySomeCertainUser();

        return ResponseEntity.status(HttpStatus.OK).body(allBlockedUsers);

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
