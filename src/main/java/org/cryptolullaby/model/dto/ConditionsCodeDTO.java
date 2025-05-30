package org.cryptolullaby.model.dto;

import java.util.List;

public record ConditionsCodeDTO (

        String next_url,

        String request_id,

        List <ConditionsCodeResultsDTO> results,

        String status

) {}
