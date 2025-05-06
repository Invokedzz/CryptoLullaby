package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends MongoRepository <Posts, String> {}
