package org.cryptolullaby.service;

import org.cryptolullaby.repository.CommentsRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public CommentsService (CommentsRepository commentsRepository) {

        this.commentsRepository = commentsRepository;

    }

}
