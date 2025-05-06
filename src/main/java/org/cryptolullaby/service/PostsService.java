package org.cryptolullaby.service;

import org.cryptolullaby.repository.PostsRepository;
import org.springframework.stereotype.Service;

@Service
public class PostsService {

    private final PostsRepository postsRepository;

    public PostsService (PostsRepository postsRepository) {

        this.postsRepository = postsRepository;

    }

}
