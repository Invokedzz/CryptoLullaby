package org.cryptolullaby.controller;

import org.cryptolullaby.service.MarketOperationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain/market")
public class CryptoMarketController {

    private final MarketOperationsService marketOperationsService;

    public CryptoMarketController (MarketOperationsService marketOperationsService) {

        this.marketOperationsService = marketOperationsService;

    }

    @GetMapping("/exchanges")
    public ResponseEntity <Void> marketExchanges (String asset_class, String locale) {

        marketOperationsService.getMarketExchanges(asset_class, locale);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/holidays")
    public ResponseEntity <Void> upcomingMarketHolidays () {

        marketOperationsService.getUpcomingMarketHolidays();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/trading/status")
    public ResponseEntity <Void> currentTradingStatus () {

        marketOperationsService.getCurrentTradingStatus();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
