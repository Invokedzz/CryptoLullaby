package org.cryptolullaby.service;

import org.bson.types.ObjectId;
import org.cryptolullaby.entity.Likes;
import org.cryptolullaby.model.enums.EntityType;
import org.cryptolullaby.repository.LikesRepository;
import org.springframework.stereotype.Service;

@Service
public class LikesService {

    private final LikesRepository likesRepository;

    public LikesService (LikesRepository likesRepository) {

        this.likesRepository = likesRepository;

    }

    public void save (Likes likes) {

        likesRepository.save(likes);

    }

    public void deleteByUserIdAndEntityId (String userId, String entityId) {

        likesRepository.deleteByUserIdAndEntityId(userId, entityId);

    }

    public long countNumberOfLikes (String entityId, EntityType entityType) {

        return likesRepository.countLikesByEntityIdAndEntityType(entityId, entityType);

    }

    public boolean hasUserLiked (String userId, String entityId, EntityType entityType) {

        return likesRepository.existsByUserIdAndEntityIdAndEntityType(userId, entityId, entityType);

    }

}
