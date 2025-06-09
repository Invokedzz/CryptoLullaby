package org.cryptolullaby.service;

import org.cryptolullaby.repository.FollowRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    public FollowService (FollowRepository followRepository) {

        this.followRepository = followRepository;

    }

}
