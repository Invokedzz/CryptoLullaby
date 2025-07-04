package org.cryptolullaby.service;

import org.cryptolullaby.entity.Follow;
import org.cryptolullaby.exception.FollowerNotFoundException;
import org.cryptolullaby.model.dto.follow.UserFollowersDTO;
import org.cryptolullaby.model.enums.FollowStatus;
import org.cryptolullaby.repository.FollowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    private static final FollowStatus PENDING_STATUS = FollowStatus.PENDING;

    private static final FollowStatus FOLLOWING_STATUS = FollowStatus.FOLLOWING;

    private static final FollowStatus BLOCKED_STATUS = FollowStatus.BLOCKED;

    public FollowService (FollowRepository followRepository) {

        this.followRepository = followRepository;

    }

    // follow request
    public void save (Follow follow) {

        followRepository.save(follow);

    }

    public UserFollowersDTO getUserFollowers (String followingId) {

        long followCount = countNumberOfFollowers(followingId);

        var followers = followRepository.findByFollowingIdAndFollowStatus(followingId, FOLLOWING_STATUS);

        return new UserFollowersDTO(followCount, followers);

    }

    public Follow findByFollowId (String id) {

        return followRepository
                .findById(id)
                .orElseThrow(() -> new FollowerNotFoundException("We weren't able to find a follower with this id: " + id));

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
                followingId, FOLLOWING_STATUS, pageable
        );

    }

    // get all blocked users
    public Page <Follow> findAllByStatusEqualsToBlockedAndFollowingId (String followingId, Pageable pageable) {

        return followRepository.findByFollowingIdAndFollowStatus(
                followingId, BLOCKED_STATUS, pageable
        );

    }

    // get all follow requests sent to user
    public Page <Follow> findAllFollowRequestsByStatusEqualsToPendingAndFollowingId (String followingId, Pageable pageable) {

        return followRepository.findByFollowingIdAndFollowStatus(
                followingId, PENDING_STATUS, pageable
        );

    }

    // get all follow requests made by user
    public Page <Follow> findAllFollowRequestsByStatusEqualsToPendingAndFollowerId (String followerId, Pageable pageable) {

        return followRepository.findByFollowerIdAndFollowStatus(
                followerId, PENDING_STATUS, pageable
        );

    }

    public void delete (Follow follow) {

        followRepository.delete(follow);

    }

    public long countNumberOfFollowers (String followingId) {

        return followRepository.countByFollowingIdAndFollowStatus(followingId, FOLLOWING_STATUS);

    }

}
