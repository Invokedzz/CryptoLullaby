package org.cryptolullaby.entity;

import org.cryptolullaby.model.enums.FollowStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("follow")
public class Follow {

    @Id
    private String id;

    private String followerId;

    private String followingId;

    private FollowStatus followStatus;

    public Follow () {}

    public Follow (String id, String followerId, String followingId, FollowStatus followStatus) {

        this.id = id;

        this.followerId = followerId;

        this.followingId = followingId;

        this.followStatus = followStatus;

    }

    public String getId () {

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

}
