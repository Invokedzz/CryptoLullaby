package org.cryptolullaby.service;

import org.cryptolullaby.model.dto.CreateCommentDTO;
import org.cryptolullaby.repository.CommentsRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public CommentsService (CommentsRepository commentsRepository) {

        this.commentsRepository = commentsRepository;

    }

    public void createComment (CreateCommentDTO createCommentDTO) {}

    public void getComments () {}

    public void getCommentById (String id) {}

    public void editCommentById (String id) {}

    public void deactivateCommentById (String id) {}

}
