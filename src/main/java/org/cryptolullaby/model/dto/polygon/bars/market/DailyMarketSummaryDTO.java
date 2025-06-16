package org.cryptolullaby.model.dto.polygon.bars.market;

import java.util.List;

public record DailyMarketSummaryDTO (

        int queryCount,

        String request_id,

        int resultsCount,

        String status,

        List <DailyMarketResultsDTO> results

) {}
