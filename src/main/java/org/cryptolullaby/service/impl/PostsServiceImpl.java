package org.cryptolullaby.service.impl;

import org.cryptolullaby.entity.Posts;
import org.cryptolullaby.exception.PostNotFoundException;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.posts.CreatePostDTO;
import org.cryptolullaby.model.dto.posts.EditPostsDTO;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.cryptolullaby.repository.PostsRepository;
import org.cryptolullaby.service.PostsService;
import org.cryptolullaby.validation.PostsValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    private final PostsValidator postsValidator;

    private final UsersServiceImpl usersService;

    private final CloudinaryServiceImpl cloudinaryService;

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

    public PagedResponseDTO <PostsDTO> getAllActivePosts (Pageable pageable) {

        var pages = postsRepository.findAllByIsActive(true, pageable);

        var posts = getPagesContentAndRenderItToDTO(pages);

        return paginationPostsStructure(pages, posts);

    }

    public PagedResponseDTO <PostsDTO> getPostsByTitle (String title, Pageable pageable) {

        return findPostsByTitle(title, pageable);

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

    private PagedResponseDTO <PostsDTO> findPostsByTitle (String title, Pageable pageable) {

        var pages = findAllActivePostsByTitle(title, pageable);

        var posts = getPagesContentAndRenderItToDTO(pages);

        postsValidator.checkIfPostListWithCertainTitleExists(posts);

        return paginationPostsStructure(pages, posts);

    }

    private Posts findPostById (String id) {

        return postsRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found!"));

    }

    private List <PostsDTO> getPagesContentAndRenderItToDTO (Page <Posts> pages) {

        return pages
                .getContent()
                .stream()
                .map(PostsDTO::new)
                .toList();

    }

    private Page <Posts> findAllActivePostsByTitle (String title, Pageable pageable) {

        return postsRepository.findAllByTitleAndIsActive(title, true, pageable);

    }

    private PagedResponseDTO <PostsDTO> paginationPostsStructure (Page <Posts> pages, List <PostsDTO> posts) {

        return new PagedResponseDTO<>(

                posts,

                pages.getNumber(),

                pages.getSize(),

                pages.getTotalPages()

        );

    }

}
