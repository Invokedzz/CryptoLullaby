package org.cryptolullaby.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("posts")
public class Posts {

    @Id
    private String id;

    private String title;

    private String description;

    private String imgUrl;

    private Integer likes;

    LocalDateTime createdAt;

    private String userId;

    public Posts () {}

    public Posts (

            String id, String title, String description, String imgUrl,
            Integer likes, LocalDateTime createdAt, String userId

    )

    {

        this.id = id;

        this.title = title;

        this.description = description;

        this.imgUrl = imgUrl;

        this.likes = likes;

        this.createdAt = createdAt;

        this.userId = userId;

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

    public String getImgUrl () {

        return imgUrl;

    }

    public void setImgUrl (String imgUrl) {

        this.imgUrl = imgUrl;

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

}
