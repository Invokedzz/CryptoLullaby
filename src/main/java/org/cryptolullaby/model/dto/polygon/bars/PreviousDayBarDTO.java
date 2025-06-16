package org.cryptolullaby.model.dto.polygon.bars;

import java.util.List;

public record PreviousDayBarDTO (

        String ticker,

        int queryCount,

        String request_id,

        int resultsCount,

        String status,

        List <DayBarResultsDTO> results

) {}
