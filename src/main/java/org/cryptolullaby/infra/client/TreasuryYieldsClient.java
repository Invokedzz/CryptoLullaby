package org.cryptolullaby.infra.client;

import org.cryptolullaby.config.PolygonConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${polygon.api.url}", name = "Treasury Yields", configuration = PolygonConfig.class)
public interface TreasuryYieldsClient {

    @GetMapping("/fed/v1/treasury-yields")
    void getTreasuryYields ();

}
