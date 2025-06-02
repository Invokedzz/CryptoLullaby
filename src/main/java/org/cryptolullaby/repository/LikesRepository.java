package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Likes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends MongoRepository <Likes, String> {
}
