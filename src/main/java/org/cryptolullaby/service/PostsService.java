package org.cryptolullaby.service;

import org.cryptolullaby.entity.Posts;
import org.cryptolullaby.exception.PostNotFoundException;
import org.cryptolullaby.model.dto.CreatePostDTO;
import org.cryptolullaby.model.dto.EditPostsDTO;
import org.cryptolullaby.repository.PostsRepository;
import org.cryptolullaby.validation.PostsValidator;
import org.springframework.stereotype.Service;

@Service
public class PostsService {

    private final PostsRepository postsRepository;

    private final PostsValidator postsValidator;

    private final UsersService usersService;

    private final CloudinaryService cloudinaryService;

    public PostsService (PostsRepository postsRepository,
                         PostsValidator postsValidator,
                         UsersService usersService,
                         CloudinaryService cloudinaryService) {

        this.postsRepository = postsRepository;

        this.postsValidator = postsValidator;

        this.usersService = usersService;

        this.cloudinaryService = cloudinaryService;

    }

    public void createPost (CreatePostDTO createPostDTO) {

        postsRepository.save(new Posts(createPostDTO));

    }

    public void getPosts () {}

    public void getPostById (String id) {}

    public void editPostById (String id, EditPostsDTO editPostsDTO) {

        var post = findPostById(id);


    }

    public void deactivatePostById (String id) {

        var post = findPostById(id);

        post.deactivate();

        postsRepository.save(post);

    }

    private Posts findPostById (String id) {

        return postsRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found!"));

    }

}
