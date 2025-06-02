package org.cryptolullaby.entity;

import org.cryptolullaby.model.dto.likes.LikeAContentDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("likes")
public class Likes {

    @Id
    private String id;

    private LocalDateTime timestamp;

    private String userId;

    private String postId;

    private String commentId;

    public Likes () {}

    public Likes (String id, LocalDateTime timestamp, String userId, String postId, String commentId) {

        this.id = id;

        this.timestamp = timestamp;

        this.userId = userId;

        this.postId = postId;

        this.commentId = commentId;

    }

    public Likes (LikeAContentDTO likeAContentDTO) {

        this.timestamp = LocalDateTime.now();

        this.userId = likeAContentDTO.userId();

        this.postId = likeAContentDTO.postId();

    }

    public String getId () {

        return id;

    }

    public LocalDateTime getTimestamp () {

        return timestamp;

    }

    public void setTimestamp (LocalDateTime timestamp) {

        this.timestamp = timestamp;

    }

    public String getUserId () {

        return userId;

    }

    public String getPostId () {

        return postId;

    }

}
