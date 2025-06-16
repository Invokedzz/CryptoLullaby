package org.cryptolullaby.orchestration.usecases.posts;

import org.cryptolullaby.entity.Posts;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.posts.CreatePostDTO;
import org.cryptolullaby.model.dto.posts.EditPostsDTO;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.service.CloudinaryService;
import org.cryptolullaby.service.LikesService;
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
    * 2) Adjust the "editPostById" method - it's deleting instead of editing -> FIXED 05/06/2025
    * 3) Adapt the cloudinaryService for posts as well -> FIXED 04/06/2025
    *
    * */

    private final PostsService postsService;

    private final LikesService likesService;

    private final CloudinaryService cloudinaryService;

    private static final boolean DEFAULT_IMAGE_ICON = false;

    public PostsUseCase (PostsService postsService, LikesService likesService, CloudinaryService cloudinaryService) {

        this.postsService = postsService;

        this.likesService = likesService;

        this.cloudinaryService = cloudinaryService;

    }

    public void createPost (CreatePostDTO createPostDTO) {

        var post = new Posts(createPostDTO);

        setupPostImage(createPostDTO.img());

        saveChangesInTheDatabase(post);

    }

    public PagedResponseDTO <PostsDTO> getAllActivePosts (Pageable pageable) {

        var pages = findAllActivePosts(pageable);

        var posts = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, posts);

    }

    public PagedResponseDTO <PostsDTO> getAllActivePostsByTitle (String title, Pageable pageable) {

        var pages = findAllActivePostsByTitle(title, pageable);

        var posts = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure(pages, posts);

    }

    public void editPostById (String id, EditPostsDTO editPostDTO) {

        var post = findPostById(id);

        post.editPost(editPostDTO);

        setupImageForEdit(post, editPostDTO);

        saveChangesInTheDatabase(post);

    }

    public void deactivatePostById (String id) {

        var post = findPostById(id);

        post.deactivate();

        saveChangesInTheDatabase(post);

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
                .map(post -> {

                    long numberOfLikes = likesService.countNumberOfLikes(post.getId(), EntityType.POST);

                    return new PostsDTO(post, numberOfLikes);

                })
                .toList();

    }

    private void setupImageForEdit (Posts post, EditPostsDTO editPostDTO) {

        if (editPostDTO.img() != null && !editPostDTO.img().isEmpty()) {

            var image = cloudinaryService.renderImage(editPostDTO.img(), DEFAULT_IMAGE_ICON);

            post.setImgUrl(image);

        }

    }

    private Page <Posts> findAllActivePosts (Pageable pageable) {

        return postsService.findAllByIsActive(pageable);

    }

    private Page <Posts> findAllActivePostsByTitle (String title, Pageable pageable) {

        return postsService.findAllActivePostsByTitle(title, pageable);

    }

    private Posts findPostById (String id) {

        return postsService.findPostById(id);

    }

    private void saveChangesInTheDatabase (Posts post) {

        postsService.save(post);

    }

    private void setupPostImage (MultipartFile file) {

        cloudinaryService.renderImage(file, DEFAULT_IMAGE_ICON);

    }

}
