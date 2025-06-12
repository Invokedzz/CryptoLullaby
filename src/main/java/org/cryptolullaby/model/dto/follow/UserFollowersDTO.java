package org.cryptolullaby.model.dto.follow;

import org.cryptolullaby.entity.Follow;

import java.util.List;

public record UserFollowersDTO (

        long numberOfFollowers,

        List <Follow> follows

) {}
