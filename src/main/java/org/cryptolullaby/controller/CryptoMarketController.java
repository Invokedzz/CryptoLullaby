package org.cryptolullaby.controller;

import org.cryptolullaby.service.MarketOperationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain/market")
public class CryptoMarketController {

    private final MarketOperationsService marketOperationsService;

    public CryptoMarketController (MarketOperationsService marketOperationsService) {

        this.marketOperationsService = marketOperationsService;

    }

    public ResponseEntity <Void> marketExchanges () {

        marketOperationsService.getMarketExchanges();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    public ResponseEntity <Void> upcomingMarketHolidays () {

        marketOperationsService.getUpcomingMarketHolidays();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    public ResponseEntity <Void> currentTradingStatus () {

        marketOperationsService.getCurrentTradingStatus();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
