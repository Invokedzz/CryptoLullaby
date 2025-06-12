package org.cryptolullaby.model.dto.users;

import org.cryptolullaby.entity.Images;
import org.cryptolullaby.entity.Interest;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.dto.follow.UserFollowersDTO;
import org.cryptolullaby.model.dto.posts.PostsDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ProfileDTO(

        String id,

        Images img,

        String username,

        String email,

        List <Interest> interests,

        LocalDateTime createdAt,

        UserFollowersDTO followers,

        List <PostsDTO> posts
)

{

    public ProfileDTO (Users user, UserFollowersDTO followers, List <PostsDTO> posts) {

        this (

                user.getId(),
                user.getImg(),
                user.getUsername(),
                user.getEmail(),
                user.getInterests(),
                user.getCreatedAt(),
                followers,
                posts

        );

    }

}
