package org.cryptolullaby.controller;

import jakarta.validation.Valid;
import org.cryptolullaby.model.dto.CreatePostDTO;
import org.cryptolullaby.service.PostsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostsController {

    private final PostsService postsService;

    public PostsController (PostsService postsService) {

        this.postsService = postsService;

    }

    @PostMapping("/posts")
    public ResponseEntity <Void> createPost (@Valid @ModelAttribute CreatePostDTO createPostDTO) {

        postsService.createPost(createPostDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/posts")
    public ResponseEntity <Void> getPosts () {

        postsService.getPosts();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/posts/{id}")
    public ResponseEntity <Void> getPostById (@PathVariable String id) {

        postsService.getPostById(id);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/posts/edit/{id}")
    public ResponseEntity <Void> editPostById (@PathVariable String id) {

        postsService.editPostById(id);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/posts/delete/{id}")
    public ResponseEntity <Void> deactivatePostById (@PathVariable String id) {

        postsService.deactivatePostById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
