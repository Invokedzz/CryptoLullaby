package org.cryptolullaby.model.dto.likes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LikeDTO(

        @NotBlank(message = "User Id cannot be blank!")
        @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "UserId has an invalid ObjectId format!")
        String userId,

        @NotBlank(message = "Entity Id cannot be blank!")
        @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "EntityId has an invalid ObjectId format!")
        String entityId

) {}
