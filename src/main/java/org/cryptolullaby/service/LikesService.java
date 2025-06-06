package org.cryptolullaby.service;

import org.cryptolullaby.entity.Likes;
import org.cryptolullaby.model.enums.EntityTypeName;
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

    public void delete (Likes likes) {

        likesRepository.delete(likes);

    }

    public long countNumberOfLikes (String entityId, EntityTypeName entityType) {

        return likesRepository.countLikesByEntityIdAndEntityType(entityId, entityType);

    }

    public boolean hasUserLiked (String userId, String entityId, EntityTypeName entityType) {

        return likesRepository.findByUserIdAndEntityIdAndEntityType(userId, entityId, entityType);

    }

}
