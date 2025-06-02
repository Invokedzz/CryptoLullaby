package org.cryptolullaby.entity;

import org.cryptolullaby.model.dto.posts.CreatePostDTO;
import org.cryptolullaby.model.dto.posts.EditPostsDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("posts")
public class Posts {

    @Id
    private String id;

    @TextIndexed
    private String title;

    private String description;

    private Images img;

    private LocalDateTime createdAt;

    private String userId;

    private Boolean isActive;

    public Posts () {}

    public Posts (

            String id, String title, String description, Images img,
            LocalDateTime createdAt, String userId, Boolean isActive

    )

    {

        this.id = id;

        this.title = title;

        this.description = description;

        this.img = img;

        this.createdAt = createdAt;

        this.userId = userId;

        this.isActive = isActive;

    }

    public Posts (CreatePostDTO createPostDTO) {

        this.title = createPostDTO.title();

        this.description = createPostDTO.description();

        this.img = new Images();

        this.createdAt = LocalDateTime.now();

        this.userId = createPostDTO.userId();

        this.isActive = true;

    }

    public void editPost (EditPostsDTO editPostsDTO) {

        if (title != null) {

            this.title = editPostsDTO.title();

        }

        if (description != null) {

            this.description = editPostsDTO.description();

        }

        if (img != null) {

            this.img = new Images();

        }

    }

    public String getId () {

        return id;

    }

    public String getTitle () {

        return title;

    }

    public void setTitle (String title) {

        this.title = title;

    }

    public String getDescription () {

        return description;

    }

    public void setDescription (String description) {

        this.description = description;

    }

    public Images getImg () {

        return img;

    }

    public void setImgUrl (Images img) {

        this.img = img;

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

    public Boolean getIsActive () {

        return isActive;

    }

    public void deactivate () {

        this.isActive = false;

    }

}
