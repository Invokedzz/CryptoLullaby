package org.cryptolullaby.model.dto;

import java.util.List;

public record ConditionsCodeResultsDTO (

        List <String> data_types,

        String description,

        String name,

        String type

) {}
