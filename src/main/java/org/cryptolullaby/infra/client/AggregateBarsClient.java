package org.cryptolullaby.infra.client;

import org.cryptolullaby.config.PolygonConfig;
import org.cryptolullaby.model.dto.polygon.bars.PreviousDayBarDTO;
import org.cryptolullaby.model.dto.polygon.bars.market.DailyMarketSummaryDTO;
import org.cryptolullaby.model.dto.polygon.bars.ticker.DailyTickerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@FeignClient(url = "${polygon.api.url}", name = "AggregateBars", configuration = PolygonConfig.class)
public interface AggregateBarsClient {

    @GetMapping("/v2/aggs/grouped/locale/global/market/crypto/{date}")
    DailyMarketSummaryDTO getDailyMarketSummary (@PathVariable String date);

    @GetMapping("/v1/open-close/crypto/{from}/{to}/{date}")
    DailyTickerDTO getDailyTickerSummary (

            @PathVariable String from,
            @PathVariable String to,
            @PathVariable String date

    );

    @GetMapping("/v2/aggs/ticker/{cryptoTicker}/prev")
    PreviousDayBarDTO getPreviousDayBar (@PathVariable String cryptoTicker);

}
