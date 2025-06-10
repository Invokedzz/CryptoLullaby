package org.cryptolullaby.model.dto.follow;

import jakarta.validation.constraints.NotNull;
import org.cryptolullaby.entity.Follow;

public record FollowDTO (

        @NotNull(message = "Follower Id field can't be null!")
        String followerId,

        @NotNull(message = "Following Id can't be null!")
        String followingId

)

{

    public FollowDTO (Follow follow) {

        this (follow.getFollowerId(), follow.getFollowingId());

    }

}
