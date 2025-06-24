package org.cryptolullaby.controller;

import org.cryptolullaby.model.dto.polygon.ConditionsCodeDTO;
import org.cryptolullaby.model.dto.polygon.MarketExchangeDTO;
import org.cryptolullaby.model.dto.polygon.MarketHolidaysDTO;
import org.cryptolullaby.model.dto.polygon.TradingStatusDTO;
import org.cryptolullaby.orchestration.MarketOperationsOrchestrationFacade;
import org.cryptolullaby.orchestration.usecases.polygon.MarketOperationsUseCase;
import org.cryptolullaby.validation.annotations.SIPValues;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/domain/market")
public class CryptoMarketController {

    private final MarketOperationsOrchestrationFacade orchestrationFacade;

    public CryptoMarketController (MarketOperationsOrchestrationFacade orchestrationFacade) {

        this.orchestrationFacade = orchestrationFacade;

    }

    @GetMapping("/exchanges")
    public ResponseEntity <MarketExchangeDTO> marketExchanges (Map <String, String> params) {

        var exchanges = orchestrationFacade.getMarketExchanges(params);

        return ResponseEntity.status(HttpStatus.OK).body(exchanges);

    }

    @GetMapping("/holidays")
    public ResponseEntity <List<MarketHolidaysDTO>> upcomingMarketHolidays () {

        var holidays = orchestrationFacade.getUpcomingMarketHolidays();

        return ResponseEntity.status(HttpStatus.OK).body(holidays);

    }

    @GetMapping("/trading/status")
    public ResponseEntity <TradingStatusDTO> currentTradingStatus () {

        var status = orchestrationFacade.getCurrentTradingStatus();

        return ResponseEntity.status(HttpStatus.OK).body(status);

    }

    @GetMapping("/conditions")
    public ResponseEntity <ConditionsCodeDTO> currentConditions (

            @RequestParam @SIPValues String sip,

            Map <String, String> params

    )

    {

        var conditions = orchestrationFacade.getConditionsCode(sip, params);

        return ResponseEntity.status(HttpStatus.OK).body(conditions);

    }

}
