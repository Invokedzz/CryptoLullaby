package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Likes;
import org.cryptolullaby.model.enums.EntityTypeName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends MongoRepository <Likes, String> {

    long countLikesByEntityIdAndEntityType(String entityId, EntityTypeName entityType);

    boolean existsByUserIdAndEntityIdAndEntityType(String userId, String entityId, EntityTypeName entityType);

    void deleteByIdAndEntityId(String id, String entityId);

    void deleteByUserIdAndEntityId(String userId, String entityId);
}
