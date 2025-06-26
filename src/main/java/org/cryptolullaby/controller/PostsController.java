package org.cryptolullaby.controller;

import jakarta.validation.Valid;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.likes.LikeDTO;
import org.cryptolullaby.model.dto.posts.CreatePostDTO;
import org.cryptolullaby.model.dto.posts.EditPostsDTO;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.cryptolullaby.model.dto.general.SystemResponseDTO;
import org.cryptolullaby.orchestration.PostsOrchestrationFacade;
import org.cryptolullaby.service.PostsService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domain/posts")
public class PostsController {

    private final PostsOrchestrationFacade orchestrationFacade;

    public PostsController (PostsOrchestrationFacade orchestrationFacade) {

        this.orchestrationFacade = orchestrationFacade;

    }

    @PostMapping
    public ResponseEntity <Void> createPost (@Valid @ModelAttribute CreatePostDTO createPostDTO) {

        orchestrationFacade.createPost(createPostDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/all")
    public ResponseEntity <PagedResponseDTO<PostsDTO>> allPosts (

            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var posts = orchestrationFacade.getAllActivePosts(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(posts);

    }

    @GetMapping
    public ResponseEntity <PagedResponseDTO<PostsDTO>> postsByTitle (

            @RequestParam String title,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var posts = orchestrationFacade.getAllActivePostsByTitle(title, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(posts);

    }

    @PutMapping
    public ResponseEntity <Void> likeACertainPost (@Valid @RequestBody LikeDTO likeDTO) {

        orchestrationFacade.likeACertainPost(likeDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity <SystemResponseDTO> editPostById (

            @PathVariable String id,
            @Valid @ModelAttribute EditPostsDTO editPostsDTO

    )

    {

        orchestrationFacade.editPostById(id, editPostsDTO);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity <Void> deactivatePostById (@PathVariable String id) {

        orchestrationFacade.deactivatePostById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
