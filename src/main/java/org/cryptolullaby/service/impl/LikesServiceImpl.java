package org.cryptolullaby.service.impl;

import org.cryptolullaby.entity.Likes;
import org.cryptolullaby.model.dto.likes.LikeAContentDTO;
import org.cryptolullaby.repository.LikesRepository;
import org.cryptolullaby.service.LikesService;
import org.cryptolullaby.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;

    public LikesServiceImpl (LikesRepository likesRepository) {

        this.likesRepository = likesRepository;

    }

    public void like (LikeAContentDTO likeAContentDTO) {

        var alreadyLiked = likesRepository.existsByEntityIdAndUserId(likeAContentDTO.entityId(), likeAContentDTO.userId());

        if (!alreadyLiked) {

            likesRepository.save(new Likes(likeAContentDTO));

        }

    }

    public void dislike (LikeAContentDTO likeAContentDTO) {



    }

}
