package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.comments.CommentsDTO;
import org.cryptolullaby.model.dto.comments.CreateCommentDTO;
import org.cryptolullaby.model.dto.comments.EditCommentDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.likes.LikeDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.orchestration.usecases.posts.LikesUseCase;
import org.cryptolullaby.orchestration.usecases.posts.comments.CommentsUseCase;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentsOrchestrationFacade {

    private final CommentsUseCase commentsUseCase;

    private final LikesUseCase likesUseCase;

    public CommentsOrchestrationFacade (CommentsUseCase commentsUseCase, LikesUseCase likesUseCase) {

        this.commentsUseCase = commentsUseCase;

        this.likesUseCase = likesUseCase;

    }

    public void createComment (CreateCommentDTO createCommentDTO) {

        commentsUseCase.createComment(createCommentDTO);

    }

    public PagedResponseDTO <CommentsDTO> getAllCommentsFromACertainPost (String postId, Pageable pageable) {

        return commentsUseCase.getAllCommentsFromACertainPost(postId, pageable);

    }

    public PagedResponseDTO <CommentsDTO> getAllCommentsMadeByACertainUser (String userId, Pageable pageable) {

        return commentsUseCase.getAllCommentsMadeByACertainUser(userId, pageable);

    }

    public void likeACertainComment (LikeDTO likeDTO) {

        likesUseCase.like(likeDTO, EntityType.COMMENT);

    }

    public void editACommentById (String id, EditCommentDTO editCommentDTO) {

        commentsUseCase.editACommentById(id, editCommentDTO);

    }

    public void deactivateACommentById (String id) {

        commentsUseCase.deactivateACommentById(id);

    }

}
