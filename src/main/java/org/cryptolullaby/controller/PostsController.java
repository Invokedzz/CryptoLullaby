package org.cryptolullaby.controller;

import jakarta.validation.Valid;
import org.cryptolullaby.model.dto.CreatePostDTO;
import org.cryptolullaby.model.dto.EditPostsDTO;
import org.cryptolullaby.model.dto.PostsDTO;
import org.cryptolullaby.model.dto.SystemResponseDTO;
import org.cryptolullaby.service.PostsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /* @GetMapping("/posts")
    public ResponseEntity <Void> getPosts () {

       // postsService.getPosts();

        return ResponseEntity.status(HttpStatus.OK).build();

    } */

    @GetMapping("/posts")
    public ResponseEntity <List<PostsDTO>> postsByTitle (@RequestParam String title,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "20") int size)
    {

        var posts = postsService.getPostsByTitle(title, page, size);

        return ResponseEntity.status(HttpStatus.OK).body(posts);

    }

    @PutMapping("/posts/edit/{id}")
    public ResponseEntity <SystemResponseDTO> editPostById (@PathVariable String id, @Valid @ModelAttribute EditPostsDTO editPostsDTO) {

        postsService.editPostById(id, editPostsDTO);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/posts/delete/{id}")
    public ResponseEntity <Void> deactivatePostById (@PathVariable String id) {

        postsService.deactivatePostById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
