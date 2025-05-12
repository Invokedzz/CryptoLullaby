package org.cryptolullaby.controller;

import jakarta.validation.Valid;
import org.cryptolullaby.model.dto.CreateCommentDTO;
import org.cryptolullaby.service.CommentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentsController {

    private final CommentsService commentsService;

    public CommentsController (CommentsService commentsService) {

        this.commentsService = commentsService;

    }

    @PostMapping("/comments")
    public ResponseEntity <Void> createComment (@Valid @Payload CreateCommentDTO createCommentDTO) {

        commentsService.createComment(createCommentDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/comments")
    public ResponseEntity <Void> getComments () {

        commentsService.getComments();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/comments/{id}")
    public ResponseEntity <Void> getCommentById (@PathVariable String id) {

        commentsService.getCommentById(id);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/comments/edit/{id}")
    public ResponseEntity <Void> editCommentById (@PathVariable String id) {

        commentsService.editCommentById(id);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/comments/delete/{id}")
    public ResponseEntity <Void> deactivateCommentById (@PathVariable String id) {

        commentsService.deactivateCommentById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
