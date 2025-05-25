package org.cryptolullaby.controller;

import org.cryptolullaby.model.dto.MarketExchangeDTO;
import org.cryptolullaby.model.dto.MarketHolidaysDTO;
import org.cryptolullaby.model.dto.TradingStatusDTO;
import org.cryptolullaby.service.MarketOperationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/domain/market")
public class CryptoMarketController {

    private final MarketOperationsService marketOperationsService;

    public CryptoMarketController (MarketOperationsService marketOperationsService) {

        this.marketOperationsService = marketOperationsService;

    }

    @GetMapping("/exchanges")
    public ResponseEntity <MarketExchangeDTO> marketExchanges (String asset_class, String locale) {

        var exchanges = marketOperationsService.getMarketExchanges(asset_class, locale);

        return ResponseEntity.status(HttpStatus.OK).body(exchanges);

    }

    @GetMapping("/holidays")
    public ResponseEntity <List<MarketHolidaysDTO>> upcomingMarketHolidays () {

        var holidays = marketOperationsService.getUpcomingMarketHolidays();

        return ResponseEntity.status(HttpStatus.OK).body(holidays);

    }

    @GetMapping("/trading/status")
    public ResponseEntity <TradingStatusDTO> currentTradingStatus () {

        var status = marketOperationsService.getCurrentTradingStatus();

        return ResponseEntity.status(HttpStatus.OK).body(status);

    }

}
