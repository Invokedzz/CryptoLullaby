package org.cryptolullaby.model.dto.comments;

import org.cryptolullaby.entity.Comments;

import java.time.LocalDateTime;

public record CommentsDTO (

        String comment,

        Integer likes,

        LocalDateTime createdAt,

        String userId,

        String postId,

        String parentId

)

{

    public CommentsDTO (Comments comments) {

        this (

                comments.getComment(), comments.getLikes(),
                comments.getCreatedAt(), comments.getUserId(), comments.getPostId(), comments.getParentId()

        );

    }

}
