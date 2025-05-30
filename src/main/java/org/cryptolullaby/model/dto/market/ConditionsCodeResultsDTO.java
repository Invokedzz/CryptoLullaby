package org.cryptolullaby.model.dto.market;

import java.util.List;

public record ConditionsCodeResultsDTO (

        List <String> data_types,

        String description,

        String name,

        String type

) {}
