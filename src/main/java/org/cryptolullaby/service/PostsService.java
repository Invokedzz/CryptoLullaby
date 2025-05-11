package org.cryptolullaby.service;

import org.cryptolullaby.model.dto.CreatePostDTO;
import org.cryptolullaby.repository.PostsRepository;
import org.springframework.stereotype.Service;

@Service
public class PostsService {

    private final PostsRepository postsRepository;

    public PostsService (PostsRepository postsRepository) {

        this.postsRepository = postsRepository;

    }

    public void createPost (CreatePostDTO createPostDTO) {}

    public void getPosts () {}

    public void getPostById (String id) {}

    public void editPostById (String id) {}

    public void deactivatePostById (String id) {}

}
