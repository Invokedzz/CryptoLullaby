package org.cryptolullaby.model.dto;

import org.cryptolullaby.entity.Images;

import java.time.LocalDateTime;

public record PostsDTO (

        Images img,

        String title,

        String description,

        Integer likes,

        LocalDateTime createdAt,

        String userId

) {}
