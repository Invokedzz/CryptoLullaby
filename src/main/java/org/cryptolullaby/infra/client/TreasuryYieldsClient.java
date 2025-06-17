package org.cryptolullaby.infra.client;

import org.cryptolullaby.config.PolygonConfig;
import org.cryptolullaby.model.dto.polygon.treasury.TreasuryYieldsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(url = "${polygon.api.url}", name = "TreasuryYields", configuration = PolygonConfig.class)
public interface TreasuryYieldsClient {

    @GetMapping("/fed/v1/treasury-yields")
    TreasuryYieldsDTO getTreasuryYields (@RequestParam String date, @RequestParam Map <String, String> params);

}
