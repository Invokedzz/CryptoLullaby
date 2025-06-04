package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.posts.CreatePostDTO;
import org.cryptolullaby.model.dto.posts.EditPostsDTO;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.cryptolullaby.orchestration.usecases.posts.PostsUseCase;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostsOrchestrationFacade {

    private final PostsUseCase postsUseCase;

    public PostsOrchestrationFacade (PostsUseCase postsUseCase) {

        this.postsUseCase = postsUseCase;

    }

    public void createPost (CreatePostDTO createPostDTO) {

        postsUseCase.createPost(createPostDTO);

    }

    public PagedResponseDTO <PostsDTO> getAllActivePosts (Pageable pageable) {

        return postsUseCase.getAllActivePosts(pageable);

    }

    public PagedResponseDTO <PostsDTO> getAllActivePostsByTitle (String title, Pageable pageable) {

        return postsUseCase.getAllActivePostsByTitle(title, pageable);

    }

    public void editPostById (String id, EditPostsDTO editPostDTO) {

        postsUseCase.editPostById(id, editPostDTO);

    }

    public void deactivatePostById (String id) {

        postsUseCase.deactivatePostById(id);

    }

}
