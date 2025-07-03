package org.cryptolullaby.entity;

import org.bson.types.ObjectId;
import org.cryptolullaby.model.dto.follow.FollowDTO;
import org.cryptolullaby.model.enums.FollowStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("follow")
public class Follow {

    @Id
    private ObjectId id;

    private String followerId;

    private String followingId;

    private FollowStatus followStatus;

    private LocalDateTime requestAt;

    public Follow () {}

    public Follow (ObjectId id, String followerId, String followingId, FollowStatus followStatus, LocalDateTime requestAt) {

        this.id = id;

        this.followerId = followerId;

        this.followingId = followingId;

        this.followStatus = followStatus;

        this.requestAt = requestAt;

    }

    public Follow (FollowDTO followDTO) {

        this.followerId = followDTO.followerId();

        this.followingId = followDTO.followingId();

        this.followStatus = FollowStatus.PENDING;

        this.requestAt = LocalDateTime.now();

    }

    public ObjectId getId () {

        return id;

    }

    public String getFollowerId () {

        return followerId;

    }

    public String getFollowingId () {

        return followingId;

    }

    public FollowStatus getFollowStatus () {

        return followStatus;

    }

    public void setFollowStatus (FollowStatus followStatus) {

        this.followStatus = followStatus;

    }

    public LocalDateTime getFollowedAt () {

        return requestAt;

    }

}
