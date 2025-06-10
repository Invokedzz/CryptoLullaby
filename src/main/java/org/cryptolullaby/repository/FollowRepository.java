package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Follow;
import org.cryptolullaby.model.dto.follow.FollowDTO;
import org.cryptolullaby.model.enums.FollowStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends MongoRepository <Follow, String> {

    Page <Follow> findByFollowingIdAndFollowStatus (String followingId, FollowStatus followStatus, Pageable pageable);

    Page <Follow> findByFollowerIdAndFollowStatus (String followerId, FollowStatus followStatus, Pageable pageable);

    Optional <Follow> findByFollowerId (String followerId);

    boolean existsByFollowerIdAndFollowingIdAndFollowStatus(String followerId, String followingId, FollowStatus followStatus);

    long countByFollowerIdAndFollowStatus(String followerId, FollowStatus followStatus);

}
