package org.cryptolullaby.model.dto.posts;

import org.cryptolullaby.entity.Images;
import org.cryptolullaby.entity.Posts;

import java.time.LocalDateTime;

public record PostsDTO (

        Images img,

        String title,

        String description,

        LocalDateTime createdAt,

        String userId

)

{

    public PostsDTO (Posts post) {

        this (post.getImg(), post.getTitle(), post.getDescription(), post.getCreatedAt(), post.getUserId());

    }

}
