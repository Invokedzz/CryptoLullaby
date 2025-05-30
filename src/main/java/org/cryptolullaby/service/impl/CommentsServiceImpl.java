package org.cryptolullaby.service.impl;

import org.cryptolullaby.entity.Comments;
import org.cryptolullaby.exception.CommentNotFoundException;
import org.cryptolullaby.model.dto.comments.CreateCommentDTO;
import org.cryptolullaby.model.dto.comments.EditCommentDTO;
import org.cryptolullaby.repository.CommentsRepository;
import org.cryptolullaby.service.CommentsService;
import org.cryptolullaby.service.PostsService;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;

    public CommentsServiceImpl (CommentsRepository commentsRepository) {

        this.commentsRepository = commentsRepository;

    }

    public void createComment (CreateCommentDTO createCommentDTO) {

        commentsRepository.save(new Comments(createCommentDTO));

    }

    public void getComments () {}

    public void getCommentById (String id) {}

    public void editCommentById (String id, EditCommentDTO editCommentDTO) {

        var comment = findCommentById(id);

        comment.editComment(editCommentDTO);

        commentsRepository.save(comment);

    }

    public void deactivateCommentById (String id) {

        var comment = findCommentById(id);

        comment.deactivate();

        commentsRepository.save(comment);

    }

    private Comments findCommentById (String id) {

        return commentsRepository
                .findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found!"));

    }

}
