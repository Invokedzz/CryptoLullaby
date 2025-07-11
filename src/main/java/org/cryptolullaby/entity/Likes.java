package org.cryptolullaby.entity;

import org.cryptolullaby.model.dto.likes.LikeDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("likes")
public class Likes {

    @Id
    private String id;

    private LocalDateTime timestamp;

    private String userId;

    private String entityId;

    private EntityType entityType;

    public Likes () {}

    public Likes (String id, LocalDateTime timestamp, String userId, String entityId, EntityType entityType) {

        this.id = id;

        this.timestamp = timestamp;

        this.userId = userId;

        this.entityId = entityId;

        this.entityType = entityType;

    }

    public Likes (LikeDTO likeAContentDTO, EntityType entityType) {

        this.timestamp = LocalDateTime.now();

        this.userId = likeAContentDTO.userId();

        this.entityId = likeAContentDTO.entityId();

        this.entityType = entityType;

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

    public String getEntityId () {

        return entityId;

    }

    public EntityType getEntityType () {

        return entityType;

    }

}
