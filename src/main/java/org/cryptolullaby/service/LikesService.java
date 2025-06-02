package org.cryptolullaby.service;

import org.cryptolullaby.model.dto.likes.LikeAContentDTO;

public interface LikesService {

    void likeACertainPost (LikeAContentDTO likeAContentDTO);

    void dislikeACertainPost (String postId);

    void likeACertainComment (LikeAContentDTO likeAContentDTO);

    void dislikeACertainComment (String commentId);

}
