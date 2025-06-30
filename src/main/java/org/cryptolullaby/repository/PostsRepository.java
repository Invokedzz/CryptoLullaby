package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends MongoRepository <Posts, String> {

    Page <Posts> findAllByIsActive (Boolean isActive, Pageable pageable);

    @Query(value = "{'title':  {$regex :  ?0, $options: 'i'}}")
    Page <Posts> findAllByTitleAndIsActive (String title, Boolean isActive, Pageable pageable);

    List <Posts> findByUserIdAndIsActive (String userId, Boolean isActive);

    boolean existsByIdAndIsActive(String id, Boolean isActive);
}
