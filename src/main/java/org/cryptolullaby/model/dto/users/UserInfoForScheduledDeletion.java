package org.cryptolullaby.model.dto.users;

import java.time.LocalDateTime;

public record UserInfoForScheduledDeletion (

        String id,

        LocalDateTime createdAt

) {}
