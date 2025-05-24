package org.cryptolullaby.infra.client;

import org.cryptolullaby.config.PolygonConfig;
import org.cryptolullaby.model.dto.MarketExchangeDTO;
import org.cryptolullaby.model.dto.MarketHolidaysDTO;
import org.cryptolullaby.model.dto.TradingStatusDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${polygon.api.url}", name = "MarketOperations", configuration = PolygonConfig.class)
public interface MarketOperationsClient {

    @GetMapping("/v3/reference/exchanges")
    MarketExchangeDTO getMarketExchanges (@RequestParam(defaultValue = "crypto") String asset_class,
                                          @RequestParam(defaultValue = "locale") String locale);

    @GetMapping("/v1/marketstatus/upcoming")
    MarketHolidaysDTO getUpcomingMarketHolidays ();

    @GetMapping("/v1/marketstatus/now")
    TradingStatusDTO getCurrentTradingStatus ();

}
