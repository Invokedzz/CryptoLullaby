package org.cryptolullaby.service;

import org.cryptolullaby.entity.Follow;
import org.cryptolullaby.exception.FollowerNotFoundException;
import org.cryptolullaby.model.enums.FollowStatus;
import org.cryptolullaby.repository.FollowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional <Follow> findByFollowerIdAndFollowingId (String followerId, String followingId) {

        return followRepository.findByFollowerIdAndFollowingId(followerId, followingId);

    }

    public void deleteByFollowerIdAndFollowingId (String followerId, String followingId) {

        followRepository.deleteByFollowerIdAndFollowingId(followerId, followingId);

    }

    // get all users followers
    public Page <Follow> findAllByStatusEqualsToFollowingAndFollowingId (String followingId, Pageable pageable) {

        return followRepository.findByFollowingIdAndFollowStatus(
                followingId, FollowStatus.FOLLOWING, pageable
        );

    }

    // get all blocked users
    public Page <Follow> findAllByStatusEqualsToBlockedAndFollowingId (String followingId, Pageable pageable) {

        return followRepository.findByFollowingIdAndFollowStatus(
                followingId, FollowStatus.BLOCKED, pageable
        );

    }

    // get all follow requests sent to user
    public Page <Follow> findAllFollowRequestsByStatusEqualsToPendingAndFollowingId (String followingId, Pageable pageable) {

        return followRepository.findByFollowingIdAndFollowStatus(
                followingId, FollowStatus.PENDING, pageable
        );

    }

    // get all follow requests made by user
    public Page <Follow> findAllFollowRequestsByStatusEqualsToPendingAndFollowerId (String followerId, Pageable pageable) {

        return followRepository.findByFollowerIdAndFollowStatus(
                followerId, FollowStatus.PENDING, pageable
        );

    }

    public void delete (Follow follow) {

        followRepository.delete(follow);

    }

    public long countNumberOfFollowers (String followerId) {

        return followRepository.countByFollowerIdAndFollowStatus(followerId, FollowStatus.FOLLOWING);

    }

}
