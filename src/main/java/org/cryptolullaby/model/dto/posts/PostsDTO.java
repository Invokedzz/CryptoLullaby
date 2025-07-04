package org.cryptolullaby.model.dto.posts;

import org.cryptolullaby.entity.Images;
import org.cryptolullaby.entity.Posts;

import java.time.LocalDateTime;

public record PostsDTO (

        Images img,

        String title,

        String description,

        LocalDateTime createdAt,

        Long likes,

        String userId

)

{

    public PostsDTO (Posts post, long likes) {

        this (

                post.getImg(), post.getTitle(), post.getDescription(),
                post.getCreatedAt(), likes, post.getUserId()

        );

    }

}
