package org.cryptolullaby.service;

import org.cryptolullaby.model.dto.comments.CommentsDTO;
import org.cryptolullaby.model.dto.comments.CreateCommentDTO;
import org.cryptolullaby.model.dto.comments.EditCommentDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.springframework.data.domain.Pageable;

public interface CommentsService {

    void createComment (CreateCommentDTO createCommentDTO);

    PagedResponseDTO <CommentsDTO> getAllActiveCommentsFromACertainPost (String postId, Pageable pageable);

    PagedResponseDTO <CommentsDTO> getAllActiveCommentsMadeByCertainUser (String userId, Pageable pageable);

    void editCommentById (String id, EditCommentDTO editCommentDTO);

    void deactivateCommentById (String id);

}
