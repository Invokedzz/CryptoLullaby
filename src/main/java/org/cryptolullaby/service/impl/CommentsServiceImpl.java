package org.cryptolullaby.service.impl;

import org.cryptolullaby.entity.Comments;
import org.cryptolullaby.entity.Posts;
import org.cryptolullaby.exception.CommentNotFoundException;
import org.cryptolullaby.model.dto.comments.CommentsDTO;
import org.cryptolullaby.model.dto.comments.CreateCommentDTO;
import org.cryptolullaby.model.dto.comments.EditCommentDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.cryptolullaby.repository.CommentsRepository;
import org.cryptolullaby.service.CommentsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;

    public CommentsServiceImpl (CommentsRepository commentsRepository) {

        this.commentsRepository = commentsRepository;

    }

    public void createComment (CreateCommentDTO createCommentDTO) {

        commentsRepository.save(new Comments(createCommentDTO));

    }

    public void getAllActiveCommentsFromACertainPost () {



    }

    public PagedResponseDTO <CommentsDTO> getAllCommentsMadeByCertainUser (String userId, Pageable pageable) {

        var pages = findAllByUserId(userId, pageable);

        var comments = getPagesContentAndRenderItToDTO(pages);

        return paginationPostsStructure(pages, comments);

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

    private List <CommentsDTO> getPagesContentAndRenderItToDTO (Page <Comments> pages) {

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

    private PagedResponseDTO <CommentsDTO> paginationPostsStructure (Page <Comments> pages, List <CommentsDTO> comments) {

        return new PagedResponseDTO<>(

                comments,

                pages.getNumber(),

                pages.getSize(),

                pages.getTotalPages()

        );

    }

    private Comments findCommentById (String id) {

        return commentsRepository
                .findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found!"));

    }

}
