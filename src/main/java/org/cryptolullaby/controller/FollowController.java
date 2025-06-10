package org.cryptolullaby.controller;

import jakarta.validation.Valid;
import org.cryptolullaby.model.dto.follow.FollowDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.orchestration.FollowOrchestrationFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/{followingId}/followers")
    public ResponseEntity <PagedResponseDTO<FollowDTO>> allOfUsersFollowers (

            @PathVariable String followingId,
            @PageableDefault(size = 5, sort = "requestAt", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var allFollowers = orchestrationFacade.getAllOfUsersFollowers(followingId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(allFollowers);

    }

    @GetMapping("/{followingId}/requests")
    public ResponseEntity <PagedResponseDTO<FollowDTO>> allFollowRequestsSentToACertainUser (

            @PathVariable String followingId,
            @PageableDefault(size = 5, sort = "requestAt", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var allFollowRequests = orchestrationFacade.getAllFollowRequestsSentToACertainUser(followingId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(allFollowRequests);

    }

    @GetMapping("/{followerId}/myrequests")
    public ResponseEntity <PagedResponseDTO<FollowDTO>> allFollowRequestsMadeByACertainUser (

            @PathVariable String followerId,
            @PageableDefault(size = 5, sort = "requestAt", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var allFollowRequests = orchestrationFacade.getAllFollowRequestsMadeByACertainUser(followerId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(allFollowRequests);

    }

    @GetMapping("/{followingId}/blocked")
    public ResponseEntity <PagedResponseDTO<FollowDTO>> allBlockedUsersBySomeCertainUser (

            @PathVariable String followingId,
            @PageableDefault(size = 5, sort = "requestAt", direction = Sort.Direction.DESC) Pageable pageable


    )

    {

        var allBlockedUsers = orchestrationFacade.getAllBlockedUsersBySomeCertainUser(followingId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(allBlockedUsers);

    }

    @PutMapping
    public ResponseEntity <Void> follow (@Valid @RequestBody FollowDTO followDTO) {

        orchestrationFacade.follow(followDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{followerId}/accept")
    public ResponseEntity <Void> acceptFollowRequest (@PathVariable String followerId) {

        orchestrationFacade.acceptFollowRequest(followerId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{followerId}/reject")
    public ResponseEntity <Void> rejectFollowRequest (@PathVariable String followerId) {

        orchestrationFacade.rejectFollowRequest(followerId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{followerId}/block")
    public ResponseEntity <Void> block (@PathVariable String followerId) {

        orchestrationFacade.block(followerId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
