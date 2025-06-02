package org.cryptolullaby.service.impl;

import org.cryptolullaby.entity.Comments;
import org.cryptolullaby.exception.CommentNotFoundException;
import org.cryptolullaby.model.dto.comments.CommentsDTO;
import org.cryptolullaby.model.dto.comments.CreateCommentDTO;
import org.cryptolullaby.model.dto.comments.EditCommentDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.repository.CommentsRepository;
import org.cryptolullaby.service.CommentsService;
import org.cryptolullaby.util.IPaginationStructure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService, IPaginationStructure <CommentsDTO, Comments> {

    private final CommentsRepository commentsRepository;

    private final UsersServiceImpl usersService;

    private final PostsServiceImpl postsService;

    public CommentsServiceImpl (CommentsRepository commentsRepository, UsersServiceImpl usersService, PostsServiceImpl postsService) {

        this.commentsRepository = commentsRepository;

        this.usersService = usersService;

        this.postsService = postsService;

    }

    public void createComment (CreateCommentDTO createCommentDTO) {

        commentsRepository.save(new Comments(createCommentDTO));

    }

    public PagedResponseDTO <CommentsDTO> getAllActiveCommentsFromACertainPost (String postId, Pageable pageable) {

        var pages = findAllByPostId(postId, pageable);

        var comments = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure (pages, comments);

    }

    public PagedResponseDTO <CommentsDTO> getAllActiveCommentsMadeByCertainUser(String userId, Pageable pageable) {

        var pages = findAllByUserId(userId, pageable);

        var comments = getPagesContentAndRenderItToDTO(pages);

        return setupPaginationStructure (pages, comments);

    }

    public void editCommentById (String id, EditCommentDTO editCommentDTO) {

        var comment = findCommentById(id);

        comment.editComment(editCommentDTO);

        commentsRepository.save(comment);

    }

    public void deactivateCommentById (String id) {

        var comment = findCommentById(id);

        comment.deactivate();

        commentsRepository.save(comment);

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

    private Page <Comments> findAllByPostId (String postId, Pageable pageable) {

        return commentsRepository.findAllByPostIdAndIsActive(postId, true, pageable);

    }

    private Page <Comments> findAllByUserId (String userId, Pageable pageable) {

        return commentsRepository.findAllByUserIdAndIsActive(userId, true, pageable);

    }

    private Comments findCommentById (String id) {

        return commentsRepository
                .findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found!"));

    }

}
