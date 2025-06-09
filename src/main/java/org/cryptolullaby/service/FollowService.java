package org.cryptolullaby.service;

import org.cryptolullaby.entity.Follow;
import org.cryptolullaby.exception.FollowerNotFoundException;
import org.cryptolullaby.model.dto.follow.FollowDTO;
import org.cryptolullaby.model.enums.FollowStatus;
import org.cryptolullaby.repository.FollowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    public FollowService (FollowRepository followRepository) {

        this.followRepository = followRepository;

    }

    // follow request
    public void save (Follow follow) {

        followRepository.save(follow);

    }

    public Follow findByFollowerId (String followerId) {

        return followRepository
                .findByFollowerId(followerId)
                .orElseThrow(() -> new FollowerNotFoundException("We weren't able to find a follower with this id: " + followerId));

    }

    // get all users followers
    public Page <FollowDTO> findAllByStatusEqualsToFollowingAndFollowingId (String followingId, Pageable pageable) {

        return followRepository.findByFollowingIdAndFollowStatus(
                followingId, FollowStatus.FOLLOWING, pageable
        );

    }

    // get all blocked users
    public Page <FollowDTO> findAllByStatusEqualsToBlockedAndFollowingId (String followingId, Pageable pageable) {

        return followRepository.findByFollowingIdAndFollowStatus(
                followingId, FollowStatus.BLOCKED, pageable
        );

    }

    // get all follow requests sent to user
    public Page <FollowDTO> findAllFollowRequestsByStatusEqualsToPendingAndFollowingId (String followingId, Pageable pageable) {

        return followRepository.findByFollowingIdAndFollowStatus(
                followingId, FollowStatus.PENDING, pageable
        );

    }

    // get all follow requests made by user
    public Page <FollowDTO> findAllFollowRequestsByStatusEqualsToPendingAndFollowerId (String followerId, Pageable pageable) {

        return followRepository.findByFollowerIdAndFollowStatus(
                followerId, FollowStatus.PENDING, pageable
        );

    }

    // reject follow
    public void deleteByFollowingId (String followingId) {

        followRepository.deleteByFollowingId(followingId);

    }

    public boolean doesStatusConditionExist (String followerId, String followingId, FollowStatus followStatus) {

        return followRepository.existsByFollowerIdAndFollowingIdAndFollowStatus(
                followerId, followingId, followStatus
        );

    }

}
