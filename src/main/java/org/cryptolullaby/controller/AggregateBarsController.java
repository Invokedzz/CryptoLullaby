package org.cryptolullaby.controller;

import jakarta.validation.constraints.NotBlank;
import org.cryptolullaby.model.dto.polygon.bars.PreviousDayBarDTO;
import org.cryptolullaby.model.dto.polygon.bars.market.DailyMarketSummaryDTO;
import org.cryptolullaby.model.dto.polygon.bars.ticker.DailyTickerDTO;
import org.cryptolullaby.service.AggregateBarsService;
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

    private final AggregateBarsService aggregateBarsService;

    public AggregateBarsController (AggregateBarsService aggregateBarsService) {

        this.aggregateBarsService = aggregateBarsService;

    }

    @GetMapping("/market/summary/{date}")
    public ResponseEntity <DailyMarketSummaryDTO> dailyMarketSummary (

            @PathVariable @NotBlank @LimitDateFormat String date

    )

    {

        var dailyMarket = aggregateBarsService.getDailyMarketSummary(date);

        return ResponseEntity.status(HttpStatus.OK).body(dailyMarket);

    }

    @GetMapping("/ticker/summary/{from}/{to}/{date}")
    public ResponseEntity <DailyTickerDTO> dailyTickerSummary (

            @PathVariable String from,
            @PathVariable @CurrencyValues String to,
            @PathVariable String date

    )

    {

        var dailyTicker = aggregateBarsService.getDailyTickerSummary(from, to, date);

        return ResponseEntity.status(HttpStatus.OK).body(dailyTicker);

    }

    @GetMapping("/previous/day/{cryptoTicker}")
    public ResponseEntity <PreviousDayBarDTO> previousDayBar (@PathVariable String cryptoTicker) {

        var dayBar = aggregateBarsService.getPreviousDayBar(cryptoTicker);

        return ResponseEntity.status(HttpStatus.OK).body(dayBar);

    }

}
