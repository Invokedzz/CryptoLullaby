package org.cryptolullaby.controller;

import org.cryptolullaby.model.dto.polygon.ConditionsCodeDTO;
import org.cryptolullaby.model.dto.polygon.MarketExchangeDTO;
import org.cryptolullaby.model.dto.polygon.MarketHolidaysDTO;
import org.cryptolullaby.model.dto.polygon.TradingStatusDTO;
import org.cryptolullaby.service.MarketOperationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/domain/market")
public class CryptoMarketController {

    private final MarketOperationsService marketOperationsService;

    public CryptoMarketController (MarketOperationsService marketOperationsService) {

        this.marketOperationsService = marketOperationsService;

    }

    @GetMapping("/exchanges")
    public ResponseEntity <MarketExchangeDTO> marketExchanges (Map <String, String> params) {

        var exchanges = marketOperationsService.getMarketExchanges(params);

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

    @GetMapping("/conditions")
    public ResponseEntity <ConditionsCodeDTO> currentConditions (@RequestParam String sip, Map <String, String> params) {

        var conditions = marketOperationsService.getConditionsCode(sip, params);

        return ResponseEntity.status(HttpStatus.OK).body(conditions);

    }

}
