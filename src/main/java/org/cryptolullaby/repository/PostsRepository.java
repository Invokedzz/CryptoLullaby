package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Posts;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends MongoRepository <Posts, String> {

    List <Posts> findByTitle (String title, Pageable pageable);

}
