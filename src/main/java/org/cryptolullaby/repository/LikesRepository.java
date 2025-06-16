package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Likes;
import org.cryptolullaby.model.enums.EntityType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends MongoRepository <Likes, String> {

    long countLikesByEntityIdAndEntityType (String entityId, EntityType entityType);

    boolean existsByUserIdAndEntityIdAndEntityType (String userId, String entityId, EntityType entityType);

    void deleteByUserIdAndEntityId (String userId, String entityId);

}
