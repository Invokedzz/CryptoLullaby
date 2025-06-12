package org.cryptolullaby.repository;

import org.cryptolullaby.entity.Follow;
import org.cryptolullaby.model.dto.follow.UserFollowersDTO;
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

    Optional <Follow> findByFollowerIdAndFollowingId (String followerId, String followingId);

    void deleteByFollowerIdAndFollowingId(String followerId, String followingId);

    List <Follow> findByFollowingIdAndFollowStatus(String followingId, FollowStatus followStatus);


    long countByFollowingIdAndFollowStatus(String followingId, FollowStatus followStatus);
}
