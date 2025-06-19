package org.cryptolullaby.controller;

import org.cryptolullaby.model.dto.polygon.bars.PreviousDayBarDTO;
import org.cryptolullaby.model.dto.polygon.bars.market.DailyMarketSummaryDTO;
import org.cryptolullaby.model.dto.polygon.bars.ticker.DailyTickerDTO;
import org.cryptolullaby.orchestration.AggregateBarsOrchestrationFacade;
import org.cryptolullaby.validation.annotations.CoinValues;
import org.cryptolullaby.validation.annotations.CryptoTickerValues;
import org.cryptolullaby.validation.annotations.CurrencyValues;
import org.cryptolullaby.validation.annotations.LimitDateFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/domain/aggregate/bars")
public class AggregateBarsController {

    private final AggregateBarsOrchestrationFacade orchestrationFacade;

    public AggregateBarsController (AggregateBarsOrchestrationFacade orchestrationFacade) {

        this.orchestrationFacade = orchestrationFacade;

    }

    @GetMapping("/market/summary/{date}")
    public ResponseEntity <DailyMarketSummaryDTO> dailyMarketSummary (@PathVariable @LimitDateFormat String date)

    {

        var dailyMarket = orchestrationFacade.getDailyMarketSummary(date);

        return ResponseEntity.status(HttpStatus.OK).body(dailyMarket);

    }

    @GetMapping("/ticker/summary/{from}/{to}/{date}")
    public ResponseEntity <DailyTickerDTO> dailyTickerSummary (

            @PathVariable @CoinValues String from,
            @PathVariable @CurrencyValues String to,
            @PathVariable String date

    )

    {

        var dailyTicker = orchestrationFacade.getDailyTickerSummary(from, to, date);

        return ResponseEntity.status(HttpStatus.OK).body(dailyTicker);

    }

    @GetMapping("/previous/day/{cryptoTicker}")
    public ResponseEntity <PreviousDayBarDTO> previousDayBar (@PathVariable @CryptoTickerValues String cryptoTicker) {

        var dayBar = orchestrationFacade.getPreviousDayBar(cryptoTicker);

        return ResponseEntity.status(HttpStatus.OK).body(dayBar);

    }

}
