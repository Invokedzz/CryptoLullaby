package org.cryptolullaby.service;

import org.cryptolullaby.entity.Posts;
import org.cryptolullaby.exception.PostNotFoundException;
import org.cryptolullaby.model.dto.CreatePostDTO;
import org.cryptolullaby.model.dto.EditPostsDTO;
import org.cryptolullaby.model.dto.PostsDTO;
import org.cryptolullaby.repository.PostsRepository;
import org.cryptolullaby.validation.PostsValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List <PostsDTO> getPostsByTitle (String title, int page, int size) {

        return findPostsByTitle(title, page, size);

    }

    public void editPostById (String id, EditPostsDTO editPostsDTO) {

        var post = findPostById(id);

        post.editPost(editPostsDTO);

        postsRepository.save(post);

    }

    public void deactivatePostById (String id) {

        var post = findPostById(id);

        post.deactivate();

        postsRepository.save(post);

    }

    private List <PostsDTO> findPostsByTitle (String title, int page, int size) {

        var pageable = getPageable(page, size);

        var posts = postsRepository
                .findByTitle(title, pageable)
                .stream()
                .filter(post -> post.getIsActive().equals(true))
                .map(PostsDTO::new)
                .toList();

        if (posts.isEmpty()) {

            throw new PostNotFoundException("We weren't able to find any posts with the title " + title);

        }

        return posts;

    }

    private Pageable getPageable (int page, int size) {

        return PageRequest.of(page, size);

    }

    private Posts findPostById (String id) {

        return postsRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found!"));

    }

}
