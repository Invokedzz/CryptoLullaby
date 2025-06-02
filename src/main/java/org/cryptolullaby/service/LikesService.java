package org.cryptolullaby.service;

import org.cryptolullaby.model.dto.likes.LikeAContentDTO;

public interface LikesService {

    void like (LikeAContentDTO likeAContentDTO);

    void dislike (LikeAContentDTO likeAContentDTO);

}
