package org.cryptolullaby.infra.client;

import org.cryptolullaby.config.PolygonConfig;
import org.cryptolullaby.model.dto.polygon.treasury.TreasuryYieldsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${polygon.api.url}", name = "TreasuryYields", configuration = PolygonConfig.class)
public interface TreasuryYieldsClient {

    @GetMapping("/fed/v1/treasury-yields")
    TreasuryYieldsDTO getTreasuryYields ();

}
