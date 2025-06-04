package org.cryptolullaby.service;

import org.cryptolullaby.entity.Posts;
import org.cryptolullaby.exception.PostNotFoundException;
import org.cryptolullaby.model.dto.posts.CreatePostDTO;
import org.cryptolullaby.repository.PostsRepository;
import org.cryptolullaby.validation.PostsValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PostsService {

    private final PostsRepository postsRepository;

    private final PostsValidator postsValidator;

    private static final boolean IS_ACTIVE = true;

    public PostsService (PostsRepository postsRepository, PostsValidator postsValidator) {

        this.postsRepository = postsRepository;

        this.postsValidator = postsValidator;

    }

    public void save (Posts post) {

        postsRepository.save(post);

    }

    public Posts findPostById (String id) {

        return postsRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found!"));

    }

    public Page <Posts> findAllByIsActive (Pageable pageable) {

        return postsRepository.findAllByIsActive(IS_ACTIVE, pageable);

    }

    public Page <Posts> findAllActivePostsByTitle (String title, Pageable pageable) {

        return postsRepository.findAllByTitleAndIsActive(
                title, IS_ACTIVE, pageable
        );

    }

}
