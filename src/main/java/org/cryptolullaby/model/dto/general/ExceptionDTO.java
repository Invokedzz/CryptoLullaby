package org.cryptolullaby.model.dto.general;

import java.time.LocalDateTime;

public record ExceptionDTO (

        Integer httpStatus,

        String message,

        LocalDateTime timestamp

) {}
