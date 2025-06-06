package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.likes.LikeDTO;
import org.cryptolullaby.model.dto.posts.CreatePostDTO;
import org.cryptolullaby.model.dto.posts.EditPostsDTO;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.cryptolullaby.model.enums.EntityTypeName;
import org.cryptolullaby.orchestration.usecases.posts.LikesUseCase;
import org.cryptolullaby.orchestration.usecases.posts.PostsUseCase;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostsOrchestrationFacade {

    private final PostsUseCase postsUseCase;

    private final LikesUseCase likesUseCase;

    public PostsOrchestrationFacade (PostsUseCase postsUseCase, LikesUseCase likesUseCase) {

        this.postsUseCase = postsUseCase;

        this.likesUseCase = likesUseCase;

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

    public void likeACertainPost (LikeDTO likeDTO) {

        likesUseCase.like(likeDTO, EntityTypeName.POST);

    }

    public void editPostById (String id, EditPostsDTO editPostDTO) {

        postsUseCase.editPostById(id, editPostDTO);

    }

    public void deactivatePostById (String id) {

        postsUseCase.deactivatePostById(id);

    }

}
