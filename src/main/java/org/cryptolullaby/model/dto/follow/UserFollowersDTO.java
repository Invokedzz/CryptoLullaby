package org.cryptolullaby.model.dto.follow;

import org.cryptolullaby.entity.Follow;

import java.util.List;

public record UserFollowersDTO (

        Long numberOfFollowers,

        List <Follow> follows

) {}
