package org.cryptolullaby.infra.client;

import org.cryptolullaby.config.PolygonConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${polygon.api.url}", name = "MarketOperations", configuration = PolygonConfig.class)
public interface MarketOperationsClient {

    @GetMapping("/v3/reference/exchanges")
    void getMarketExchanges ();

    @GetMapping("/v1/marketstatus/upcoming")
    void getUpcomingMarketHolidays ();

    @GetMapping("/v1/marketstatus/now")
    void getCurrentTradingStatus ();

}
