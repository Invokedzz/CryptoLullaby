package org.cryptolullaby.orchestration.usecases.posts;

import org.cryptolullaby.entity.Likes;
import org.cryptolullaby.model.dto.likes.LikeDTO;
import org.cryptolullaby.model.enums.EntityTypeName;
import org.cryptolullaby.service.LikesService;
import org.springframework.stereotype.Service;

@Service
public class LikesUseCase {

    private final LikesService likesService;

    public LikesUseCase (LikesService likesService) {

        this.likesService = likesService;

    }

    public void like (LikeDTO likeAContentDTO, EntityTypeName entityTypeName) {

        var hasLiked = hasUserAlreadyLiked(
                likeAContentDTO.userId(),
                likeAContentDTO.entityId(),
                entityTypeName
        );

        if (!hasLiked) {

            likesService.save(new Likes(likeAContentDTO, entityTypeName));

            return;

        }

        likesService.delete(new Likes(likeAContentDTO, entityTypeName));

    }

    public void countTheNumberOfLikes () {}

    private boolean hasUserAlreadyLiked (String userId, String entityId, EntityTypeName entityType) {

        return likesService.hasUserLiked(userId, entityId, entityType);

    }

}
