package org.cryptolullaby.service.impl;

import org.cryptolullaby.entity.Likes;
import org.cryptolullaby.model.dto.likes.LikeAContentDTO;
import org.cryptolullaby.repository.LikesRepository;
import org.cryptolullaby.service.LikesService;
import org.springframework.stereotype.Service;

@Service
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;

    public LikesServiceImpl (LikesRepository likesRepository) {

        this.likesRepository = likesRepository;

    }

    public void likeACertainPost (LikeAContentDTO likeAContentDTO) {

        var alreadyLiked = likesRepository.existsByPostIdAndUserId(likeAContentDTO.postId(), likeAContentDTO.userId());

        if (!alreadyLiked) {

            likesRepository.save(new Likes(likeAContentDTO));

        }

    }

    public void dislikeACertainPost (String postId) {

        likesRepository.deleteLikesByPostId(postId);

    }

    public void likeACertainComment (LikeAContentDTO likeAContentDTO) {

        var alreadyLiked = likesRepository.existsByCommentIdAndUserId(likeAContentDTO.commentId(), likeAContentDTO.userId());

        if (!alreadyLiked) {

            likesRepository.save(new Likes(likeAContentDTO));

        }

    }

    public void dislikeACertainComment (String commentId) {

        likesRepository.deleteLikesByCommentId(commentId);

    }

}
