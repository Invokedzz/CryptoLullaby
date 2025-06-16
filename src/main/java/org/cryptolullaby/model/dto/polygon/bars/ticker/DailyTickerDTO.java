package org.cryptolullaby.model.dto.polygon.bars.ticker;

import java.util.List;

public record DailyTickerDTO (

        double close,

        List <DailyTradesDTO> closingTrades,

        String day,

        boolean isUTC,

        List <DailyTradesDTO> openTrades

) {}
