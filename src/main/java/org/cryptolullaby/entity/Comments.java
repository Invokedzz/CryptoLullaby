package org.cryptolullaby.entity;

import org.cryptolullaby.model.dto.comments.CreateCommentDTO;
import org.cryptolullaby.model.dto.comments.EditCommentDTO;
import org.cryptolullaby.model.enums.EntityType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("comments")
public class Comments {

    @Id
    private String id;

    private String comment;

    private Integer likes;

    private LocalDateTime createdAt;

    private String userId;

    private String postId;

    private String parentId;

    private EntityType entityType;

    private Boolean isActive;

    public Comments () {}

    public Comments (

            String id, String comment, Integer likes, LocalDateTime createdAt, String userId,
            String postId, String parentId, EntityType entityType, Boolean isActive

    )

    {

        this.id = id;

        this.comment = comment;

        this.likes = likes;

        this.createdAt = createdAt;

        this.userId = userId;

        this.postId = postId;

        this.parentId = parentId;

        this.entityType = entityType;

        this.isActive = isActive;

    }

    public Comments (CreateCommentDTO createCommentDTO) {

        this.comment = createCommentDTO.comment();

        this.likes = 0;

        this.createdAt = LocalDateTime.now();

        this.userId = createCommentDTO.userId();

        this.postId = createCommentDTO.postId();

        this.parentId = createCommentDTO.parentId();

        this.entityType = EntityType.COMMENT;

        this.isActive = true;

    }

    public void editComment (EditCommentDTO editCommentDTO) {

        if (editCommentDTO.comment() != null) {

            this.comment = editCommentDTO.comment();

        }

    }

    public String getId () {

        return id;

    }

    public String getComment () {

        return comment;

    }

    public Integer getLikes () {

        return likes;

    }

    public void setLikes (Integer likes) {

        this.likes = likes;

    }

    public LocalDateTime getCreatedAt () {

        return createdAt;

    }

    public void setCreatedAt (LocalDateTime createdAt) {

        this.createdAt = createdAt;

    }

    public String getUserId () {

        return userId;

    }

    public void setUserId (String userId) {

        this.userId = userId;

    }

    public String getPostId () {

        return postId;

    }

    public String getParentId () {

        return parentId;

    }

    public EntityType getEntityType () {

        return entityType;

    }

    public void setEntityType (EntityType entityType) {

        this.entityType = entityType;

    }

    public void deactivate () {

        this.isActive = false;

    }

}
