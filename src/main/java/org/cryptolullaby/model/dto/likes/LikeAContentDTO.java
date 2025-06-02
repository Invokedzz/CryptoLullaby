package org.cryptolullaby.model.dto.likes;

import org.cryptolullaby.model.enums.EntityTypeName;

public record LikeAContentDTO (

        String userId,

        String entityId,

        EntityTypeName entityType

) {}
