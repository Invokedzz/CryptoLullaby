package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends MongoRepository <Follow, String> {
}
