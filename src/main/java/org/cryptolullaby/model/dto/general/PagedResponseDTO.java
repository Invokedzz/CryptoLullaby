package org.cryptolullaby.model.dto.general;

import java.util.List;

public record PagedResponseDTO <T> (

        List <T> content,

        int page,

        int size,

        long totalPages

) {}
