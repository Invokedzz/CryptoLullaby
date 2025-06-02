package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Likes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends MongoRepository <Likes, String> {

    boolean existsByPostIdAndUserId (String postId, String userId);

    void deleteLikesByCommentId(String commentId);

    boolean existsByCommentIdAndUserId(String commentId, String userId);

    void deleteLikesByPostId(String postId);
}
