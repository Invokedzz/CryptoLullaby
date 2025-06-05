package org.cryptolullaby.orchestration.usecases.posts;

import org.cryptolullaby.entity.Posts;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.posts.CreatePostDTO;
import org.cryptolullaby.model.dto.posts.EditPostsDTO;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.cryptolullaby.service.CloudinaryService;
import org.cryptolullaby.service.PostsService;
import org.cryptolullaby.util.IPaginationStructure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PostsUseCase implements IPaginationStructure <PostsDTO, Posts> {

    /*
    *
    * TO DO:
    *
    * 1) Don't let the dumb as heck user edit a deactivated post -> FIXED 04/06/2025
    * 2) Adjust the "editPostById" method - it's deleting instead of editing
    * 3) Adapt the cloudinaryService for posts as well -> FIXED 04/06/2025
    *
    * */

    private final PostsService postsService;

    private final CloudinaryService cloudinaryService;

    private static final boolean DEFAULT_IMAGE_ICON = false;

    public PostsUseCase (PostsService postsService, CloudinaryService cloudinaryService) {

        this.postsService = postsService;

        this.cloudinaryService = cloudinaryService;

    }

    public void createPost (CreatePostDTO createPostDTO) {

        var post = new Posts(createPostDTO);

        setupPostImage(createPostDTO.img());

        postsService.save(post);

    }

    public PagedResponseDTO <PostsDTO> getAllActivePosts (Pageable pageable) {

        var pages = postsService.findAllByIsActive(pageable);

        var posts = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, posts);

    }

    public PagedResponseDTO <PostsDTO> getAllActivePostsByTitle (String title, Pageable pageable) {

        var pages = postsService.findAllActivePostsByTitle(title, pageable);

        var posts = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, posts);

    }

    public void editPostById (String id, EditPostsDTO editPostDTO) {

        var post = postsService.findPostById(id);

        post.editPost(editPostDTO);

        postsService.save(post);

    }

    public void deactivatePostById (String id) {

        var post = postsService.findPostById(id);

        post.deactivate();

        postsService.save(post);

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

    @Override
    public List <PostsDTO> getPagesContentAndRenderItToDTO (Page <Posts> pages) {

        return pages
                .getContent()
                .stream()
                .map(PostsDTO::new)
                .toList();

    }

    private void setupPostImage (MultipartFile file) {

        cloudinaryService.renderImage(file, DEFAULT_IMAGE_ICON);

    }

}
