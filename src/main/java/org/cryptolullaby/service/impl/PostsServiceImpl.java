package org.cryptolullaby.service.impl;

import org.cryptolullaby.entity.Posts;
import org.cryptolullaby.exception.PostNotFoundException;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.posts.CreatePostDTO;
import org.cryptolullaby.model.dto.posts.EditPostsDTO;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.cryptolullaby.repository.PostsRepository;
import org.cryptolullaby.service.CloudinaryService;
import org.cryptolullaby.service.PostsService;
import org.cryptolullaby.util.IPaginationStructure;
import org.cryptolullaby.validation.PostsValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService, IPaginationStructure <PostsDTO, Posts> {

    private final PostsRepository postsRepository;

    private final PostsValidator postsValidator;

    private final CloudinaryService cloudinaryService;

    public PostsServiceImpl (PostsRepository postsRepository,
                            PostsValidator postsValidator,
                            CloudinaryService cloudinaryService) {

        this.postsRepository = postsRepository;

        this.postsValidator = postsValidator;

        this.cloudinaryService = cloudinaryService;

    }

    /*
    *
    * Public methods
    *
    * */

    public void createPost (CreatePostDTO createPostDTO) {

        var post = new Posts(createPostDTO);

        cloudinaryService.checkImgPropertiesThenSetURL(post.getImg(), createPostDTO.img());

        postsRepository.save(post);

    }

    public PagedResponseDTO <PostsDTO> getAllActivePosts (Pageable pageable) {

        var pages = postsRepository.findAllByIsActive(true, pageable);

        var posts = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure (pages, posts);

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

    public void likeACertainPost () {}

    public void dislikeACertainPost () {}

    public void likeACertainComment () {}

    public void dislikeACertainComment () {}

    @Override
    public List <PostsDTO> getPagesContentAndRenderItToDTO (Page <Posts> pages) {

        return pages
                .getContent()
                .stream()
                .map(PostsDTO::new)
                .toList();

    }

    @Override
    public PagedResponseDTO <PostsDTO> setupPaginationStructure (Page <Posts> pages, List <PostsDTO> elements) {

        return new PagedResponseDTO<>(

                elements,

                pages.getNumber(),

                pages.getSize(),

                pages.getTotalPages()

        );

    }

    /*
    *
    * Private methods
    *
    * */

    private PagedResponseDTO <PostsDTO> findPostsByTitle (String title, Pageable pageable) {

        var pages = findAllActivePostsByTitle(title, pageable);

        var posts = getPagesContentAndRenderItToDTO(pages);

        postsValidator.checkIfPostListWithCertainTitleExists(posts);

        return setupPaginationStructure (pages, posts);

    }

    private Posts findPostById (String id) {

        return postsRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found!"));

    }

    private Page <Posts> findAllActivePostsByTitle (String title, Pageable pageable) {

        return postsRepository.findAllByTitleAndIsActive(title, true, pageable);

    }

}
