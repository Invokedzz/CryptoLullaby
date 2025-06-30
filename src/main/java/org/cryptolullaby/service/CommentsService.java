package org.cryptolullaby.service;

import org.cryptolullaby.entity.Comments;
import org.cryptolullaby.exception.CommentNotFoundException;
import org.cryptolullaby.repository.CommentsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;

    private static final boolean IS_ACTIVE = true;

    public CommentsService (CommentsRepository commentsRepository) {

        this.commentsRepository = commentsRepository;

    }

    public void save (Comments comments) {

        commentsRepository.save(comments);

    }

    public Comments findCommentById (String id) {

        return commentsRepository
                .findById(id)
                .orElseThrow(() -> new CommentNotFoundException("We weren't able to find comment with this id: " + id));

    }

    public boolean doesCommentExist (String id) {

        return commentsRepository.existsByIdAndIsActive(
                id, IS_ACTIVE
        );

    }

    public Page <Comments> findAllByUserIdAndIsActive (String userId, Pageable pageable) {

        return commentsRepository.findAllByUserIdAndIsActive(
                userId, IS_ACTIVE, pageable
        );

    }

    public Page <Comments> findAllByPostIdAndIsActive (String postId, Pageable pageable) {

        return commentsRepository.findAllByPostIdAndIsActive(
                postId, IS_ACTIVE, pageable
        );

    }

}
