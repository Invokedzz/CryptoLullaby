package org.cryptolullaby.model.dto;

import org.cryptolullaby.entity.Interest;
import org.cryptolullaby.entity.Users;
import org.cryptolullaby.model.enums.InterestName;

import java.time.LocalDateTime;
import java.util.List;

public record ProfileDTO (

        String imgUrl,

        String username,

        String email,

        List <Interest> interests,

        LocalDateTime createdAt

) {

    public ProfileDTO (Users user) {

        this (user.getImgUrl(), user.getUsername(), user.getEmail(), user.getInterests(), user.getCreatedAt());

    }

}
