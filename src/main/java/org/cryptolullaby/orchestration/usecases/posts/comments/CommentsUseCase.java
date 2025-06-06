package org.cryptolullaby.orchestration.usecases.posts.comments;

import org.cryptolullaby.entity.Comments;
import org.cryptolullaby.entity.Posts;
import org.cryptolullaby.model.dto.comments.CommentsDTO;
import org.cryptolullaby.model.dto.comments.CreateCommentDTO;
import org.cryptolullaby.model.dto.comments.EditCommentDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.service.CommentsService;
import org.cryptolullaby.service.PostsService;
import org.cryptolullaby.util.IPaginationStructure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsUseCase implements IPaginationStructure <CommentsDTO, Comments> {

    private final CommentsService commentsService;

    private final PostsService postsService;

    public CommentsUseCase (CommentsService commentsService, PostsService postsService) {

        this.commentsService = commentsService;

        this.postsService = postsService;

    }

    public void createComment (CreateCommentDTO createCommentDTO) {

        var comment = new Comments(createCommentDTO);

        saveChangesInTheDatabase(comment);

    }

    public PagedResponseDTO <CommentsDTO> getAllCommentsFromACertainPost (String postId, Pageable pageable) {

        var pages = findAllByPostIdAndIsActive(postId, pageable);

        var comments = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure (pages, comments);

    }

    public PagedResponseDTO <CommentsDTO> getAllCommentsMadeByACertainUser (String userId, Pageable pageable) {

        var pages = findAllByUserIdAndIsActive(userId, pageable);

        var comments = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure (pages, comments);

    }

    public void editACommentById (String id, EditCommentDTO editCommentDTO) {

        var comment = findCommentById(id);

        comment.editComment(editCommentDTO);

        saveChangesInTheDatabase(comment);

    }

    public void deactivateACommentById (String id) {

        var comment = findCommentById(id);

        comment.deactivate();

        saveChangesInTheDatabase(comment);

    }

    @Override
    public PagedResponseDTO <CommentsDTO> setupPaginationStructure (Page <Comments> pages, List <CommentsDTO> elements) {

        return new PagedResponseDTO<>(

                elements,

                pages.getNumber(),

                pages.getSize(),

                pages.getTotalPages()

        );

    }

    @Override
    public List <CommentsDTO> getPagesContentAndRenderItToDTO (Page <Comments> pages) {

        return pages
                .getContent()
                .stream()
                .map(CommentsDTO::new)
                .toList();

    }

    private void saveChangesInTheDatabase (Comments comments) {

        commentsService.save(comments);

    }

    private Comments findCommentById (String id) {

        return commentsService.findCommentById(id);

    }

    private Page <Comments> findAllByUserIdAndIsActive (String userId, Pageable pageable) {

        return commentsService.findAllByUserIdAndIsActive(userId, pageable);

    }

    private Page <Comments> findAllByPostIdAndIsActive (String postId, Pageable pageable) {

        return commentsService.findAllByPostIdAndIsActive(postId, pageable);

    }

}
