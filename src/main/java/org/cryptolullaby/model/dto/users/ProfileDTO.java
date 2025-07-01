package org.cryptolullaby.model.dto.users;

import org.cryptolullaby.entity.Images;
import org.cryptolullaby.entity.Interest;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.dto.follow.UserFollowersDTO;
import org.cryptolullaby.model.dto.posts.PostsDTO;
import org.cryptolullaby.model.enums.ProfileStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ProfileDTO(

        String id,

        Images img,

        String username,

        String email,

        List <Interest> interests,

        List <ProfileStatus> status,

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
                user.getStatus(),
                user.getCreatedAt(),
                followers,
                posts

        );

    }

}
