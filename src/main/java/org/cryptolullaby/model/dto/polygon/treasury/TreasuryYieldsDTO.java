package org.cryptolullaby.model.dto.polygon.treasury;

import java.util.List;

public record TreasuryYieldsDTO (

        String next_url,

        String request_id,

        List <TreasuryResultsDTO> results,

        String status

) {}
