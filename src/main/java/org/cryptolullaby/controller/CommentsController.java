package org.cryptolullaby.controller;

import jakarta.validation.Valid;
import org.cryptolullaby.model.dto.comments.CommentsDTO;
import org.cryptolullaby.model.dto.comments.CreateCommentDTO;
import org.cryptolullaby.model.dto.comments.EditCommentDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.orchestration.CommentsOrchestrationFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domain/comments")
public class CommentsController {

    private final CommentsOrchestrationFacade orchestrationFacade;

    public CommentsController (CommentsOrchestrationFacade orchestrationFacade) {

        this.orchestrationFacade = orchestrationFacade;

    }

    @PostMapping
    public ResponseEntity <Void> createComment (@Valid @RequestBody CreateCommentDTO createCommentDTO) {

       orchestrationFacade.createComment(createCommentDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/")
    public ResponseEntity <PagedResponseDTO<CommentsDTO>> allCommentsFromACertainPost (

            @RequestParam String postId,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var comments = orchestrationFacade.getAllCommentsFromACertainPost(postId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(comments);

    }

    @GetMapping
    public ResponseEntity <PagedResponseDTO<CommentsDTO>> allCommentsMadeByACertainUser (

            @RequestParam String userId,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var comments = orchestrationFacade.getAllCommentsMadeByACertainUser(userId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(comments);

    }

    /*
    @PostMapping("/{commentId}/{userId}/like")
    public ResponseEntity <Void> likeACertainComment (

            @PathVariable String commentId,
            @PathVariable String userId

    )

    {

        commentsService.likeComment();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PostMapping("/{commentId}/{userId}/dislike")
    public ResponseEntity <Void> dislikeACertainComment (

            @PathVariable String commentId,
            @PathVariable String userId

    )

    {

        commentsService.dislikeComment();

        return ResponseEntity.status(HttpStatus.OK).build();

    }
    */

    @PutMapping("/edit/{id}")
    public ResponseEntity <Void> editCommentById (@PathVariable String id, @Valid @RequestBody EditCommentDTO editCommentDTO) {

        orchestrationFacade.editACommentById(id, editCommentDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity <Void> deactivateCommentById (@PathVariable String id) {

        orchestrationFacade.deactivateACommentById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
