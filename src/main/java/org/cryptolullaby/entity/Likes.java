package org.cryptolullaby.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("likes")
public class Likes {

    @Id
    private String id;

    private int counter;

    private LocalDateTime timestamp;

    private String userId;

    private String postId;

    public Likes () {}

    public Likes (String id, int counter, LocalDateTime timestamp, String userId, String postId) {

        this.id = id;

        this.counter = counter;

        this.timestamp = timestamp;

        this.userId = userId;

        this.postId = postId;

    }

    public String getId () {

        return id;

    }

    public int getCounter () {

        return counter;

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

    public void like () {

        this.counter++;

    }

    public void dislike () {

        this.counter--;

    }

}
