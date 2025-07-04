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

    // todo: need to fix this method, it's returning
    // "message": "Query { \"$java\" : Query: { \"followerId\" : \"683f30fc7d61cd6086ca0363\"},
    // Fields: {}, Sort: {} } returned non unique result".
    // Rewrite this shit method :)
    // FIXED 03/07/25

    @PutMapping("/{id}/accept")
    public ResponseEntity <Void> acceptFollowRequest (@PathVariable String id) {

        orchestrationFacade.acceptFollowRequest(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{id}/reject")
    public ResponseEntity <Void> rejectFollowRequest (@PathVariable String id) {

        orchestrationFacade.rejectFollowRequest(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{id}/block")
    public ResponseEntity <Void> block (@PathVariable String id) {

        orchestrationFacade.block(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
