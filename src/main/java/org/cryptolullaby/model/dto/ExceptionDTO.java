package org.cryptolullaby.model.dto;

import java.time.LocalDateTime;

public record ExceptionDTO (

        Integer httpStatus,

        String message,

        LocalDateTime timestamp

) {}
