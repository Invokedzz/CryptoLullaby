package org.cryptolullaby.model.dto.follow;

import org.cryptolullaby.entity.Follow;

public record FollowDTO (

        String followerId,

        String followingId

)

{

    public FollowDTO (Follow follow) {

        this (follow.getFollowerId(), follow.getFollowingId());

    }

}
