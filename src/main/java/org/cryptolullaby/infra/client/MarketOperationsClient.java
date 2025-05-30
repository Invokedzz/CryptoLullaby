package org.cryptolullaby.infra.client;

import org.cryptolullaby.config.PolygonConfig;
import org.cryptolullaby.model.dto.market.ConditionsCodeDTO;
import org.cryptolullaby.model.dto.market.MarketExchangeDTO;
import org.cryptolullaby.model.dto.market.MarketHolidaysDTO;
import org.cryptolullaby.model.dto.market.TradingStatusDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(url = "${polygon.api.url}", name = "MarketOperations", configuration = PolygonConfig.class)
public interface MarketOperationsClient {

    @GetMapping("/v3/reference/exchanges")
    MarketExchangeDTO getMarketExchanges (@RequestParam Map <String, String> params);

    @GetMapping("/v1/marketstatus/upcoming")
    List <MarketHolidaysDTO> getUpcomingMarketHolidays ();

    @GetMapping("/v1/marketstatus/now")
    TradingStatusDTO getCurrentTradingStatus ();

    @GetMapping("/v3/reference/conditions")
    ConditionsCodeDTO getConditionsCode (@RequestParam Map <String, String> params);

}
