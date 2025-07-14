package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends MongoRepository <Comments, String> {

    Page <Comments> findAllByUserIdAndIsActive (String userId, Boolean isActive, Pageable pageable);

    Page <Comments> findAllByPostIdAndIsActive (String postId, Boolean isActive, Pageable pageable);

    boolean existsByIdAndIsActive (String id, Boolean isActive);

}
