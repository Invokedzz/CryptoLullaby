package org.cryptolullaby.service;

import org.cryptolullaby.model.dto.likes.LikeAContentDTO;
import org.cryptolullaby.model.enums.EntityTypeName;

public interface LikesService {

    void like (LikeAContentDTO likeAContentDTO);

    void dislike (LikeAContentDTO likeAContentDTO);

    long countTheNumberOfLikes (String entityId, EntityTypeName entityTypeName);

}
