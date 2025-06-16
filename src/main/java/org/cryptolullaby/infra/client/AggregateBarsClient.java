package org.cryptolullaby.infra.client;

import org.cryptolullaby.config.PolygonConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${polygon.api.url}", name = "Aggregate Bars", configuration = PolygonConfig.class)
public interface AggregateBarsClient {

    @GetMapping("/v2/aggs/grouped/locale/global/market/crypto/{date}")
    void getDailyMarketSummary (@PathVariable String date);

    @GetMapping("/v1/open-close/crypto/{from}/{to}/{date}")
    void getDailyTickerSummary (

            @PathVariable String from,
            @PathVariable String to,
            @PathVariable String date

    );

    @GetMapping("/v2/aggs/ticker/{cryptoTicker}/prev")
    void getPreviousDayBar (@PathVariable String cryptoTicker);

}
