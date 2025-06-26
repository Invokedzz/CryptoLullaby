package org.cryptolullaby.model.dto.follow;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.cryptolullaby.entity.Follow;
import org.cryptolullaby.model.enums.FollowStatus;

import java.time.LocalDateTime;

public record FollowDTO (

        @NotBlank(message = "Follower Id field cannot be blank!")
        @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "FollowerId has an invalid ObjectId format!")
        String followerId,

        @NotBlank(message = "Following Id cannot be blank!")
        @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "FollowingId has an invalid ObjectId format!")
        String followingId,

        FollowStatus status,

        LocalDateTime requestAt

)

{

    public FollowDTO (Follow follow) {

        this (

                follow.getFollowerId(), follow.getFollowingId(),
                follow.getFollowStatus(), follow.getFollowedAt()

        );

    }

}
