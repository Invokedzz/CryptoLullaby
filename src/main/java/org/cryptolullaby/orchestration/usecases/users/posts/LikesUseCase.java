package org.cryptolullaby.orchestration.usecases.users.posts;

import org.cryptolullaby.entity.Likes;
import org.cryptolullaby.model.dto.likes.LikeDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.service.LikesService;
import org.springframework.stereotype.Service;

@Service
public class LikesUseCase {

    private final LikesService likesService;

    public LikesUseCase (LikesService likesService) {

        this.likesService = likesService;

    }

    public void like (LikeDTO likeAContentDTO, EntityType entityTypeName) {

        var hasLiked = hasUserAlreadyLiked(
                likeAContentDTO.userId(),
                likeAContentDTO.entityId(),
                entityTypeName
        );

        if (!hasLiked) {

            saveChangesInTheDatabase(new Likes(likeAContentDTO, entityTypeName));

            return;

        }

        deleteLikeByUserIdAndEntityId(likeAContentDTO.userId(), likeAContentDTO.entityId());

    }

    private void saveChangesInTheDatabase (Likes like) {

        likesService.save(like);

    }

    private void deleteLikeByUserIdAndEntityId (String userId, String entityId) {

        likesService.deleteByUserIdAndEntityId(userId, entityId);

    }

    private boolean hasUserAlreadyLiked (String userId, String entityId, EntityType entityType) {

        return likesService.hasUserLiked(userId, entityId, entityType);

    }

}
