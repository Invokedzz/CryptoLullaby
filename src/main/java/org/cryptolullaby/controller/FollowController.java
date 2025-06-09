package org.cryptolullaby.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domain/follow")
public class FollowController {

    @GetMapping("/{id}/followers")
    public ResponseEntity <Void> allOfUsersFollowers (@PathVariable String id) {

        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}/requests")
    public ResponseEntity <Void> allFollowRequestsSentToACertainUser (@PathVariable String id) {

        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}/myrequests")
    public ResponseEntity <Void> allFollowRequestsMadeByACertainUser (@PathVariable String id) {

        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}/blocked")
    public ResponseEntity <Void> allBlockedUsersBySomeCertainUser (@PathVariable String id) {

        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity <Void> follow (@PathVariable String id) {

        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}/accept")
    public ResponseEntity <Void> acceptFollowRequest (@PathVariable String id) {

        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}/reject")
    public ResponseEntity <Void> rejectFollowRequest (@PathVariable String id) {

        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}/block")
    public ResponseEntity <Void> block (@PathVariable String id) {

        return ResponseEntity.ok().build();

    }

}
