package org.cryptolullaby.service.impl;

import org.cryptolullaby.entity.Posts;
import org.cryptolullaby.exception.PostNotFoundException;
import org.cryptolullaby.model.dto.posts.CreatePostDTO;
import org.cryptolullaby.model.dto.posts.EditPostsDTO;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.cryptolullaby.repository.PostsRepository;
import org.cryptolullaby.service.PostsService;
import org.cryptolullaby.validation.PostsValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    private final PostsValidator postsValidator;

    private final UsersServiceImpl usersService;

    private final CloudinaryServiceImpl cloudinaryService;

    private static final int PAGE = 0;

    private static final int SIZE = 20;

    public PostsServiceImpl (PostsRepository postsRepository,
                            PostsValidator postsValidator,
                            UsersServiceImpl usersService,
                            CloudinaryServiceImpl cloudinaryService) {

        this.postsRepository = postsRepository;

        this.postsValidator = postsValidator;

        this.usersService = usersService;

        this.cloudinaryService = cloudinaryService;

    }

    public void createPost (CreatePostDTO createPostDTO) {

        var post = new Posts(createPostDTO);

        cloudinaryService.checkImgPropertiesThenSetURL(post.getImg(), createPostDTO.img());

        postsRepository.save(post);

    }

    public List <PostsDTO> getPostsByTitle (String title, int page, int size) {

        return findPostsByTitle(title);

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

    private List <PostsDTO> findPostsByTitle (String title) {

        var pageable = getPageable();

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

    private Pageable getPageable () {

        return PageRequest.of(PAGE, SIZE);

    }

    private Posts findPostById (String id) {

        return postsRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found!"));

    }

}
