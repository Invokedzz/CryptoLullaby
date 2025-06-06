package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Likes;
import org.cryptolullaby.model.enums.EntityTypeName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends MongoRepository <Likes, String> {

    boolean existsByEntityIdAndUserId (String entityId, String userId);

    long countLikesByEntityIdAndEntityType(String entityId, EntityTypeName entityType);

    boolean findByUserIdAndEntityIdAndEntityType(String userId, String entityId, EntityTypeName entityType);
}
