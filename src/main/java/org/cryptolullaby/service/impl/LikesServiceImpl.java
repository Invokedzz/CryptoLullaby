package org.cryptolullaby.service.impl;

import org.cryptolullaby.repository.LikesRepository;
import org.cryptolullaby.service.LikesService;
import org.springframework.stereotype.Service;

@Service
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;

    public LikesServiceImpl (LikesRepository likesRepository) {

        this.likesRepository = likesRepository;

    }

    public void likeACertainPost (String postId) {

    }

    public void dislikeACertainPost (String postId) {

    }

    public void likeACertainComment (String commentId) {

    }

    public void dislikeACertainComment (String commentId) {

    }

}
