package org.cryptolullaby.controller;

import org.cryptolullaby.service.AggregateBarsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain/aggregate/bars")
public class AggregateBarsController {

    private final AggregateBarsService aggregateBarsService;

    public AggregateBarsController (AggregateBarsService aggregateBarsService) {

        this.aggregateBarsService = aggregateBarsService;

    }

    @GetMapping("/market/summary")
    public ResponseEntity <Void> dailyMarketSummary () {

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/ticker/summary")
    public ResponseEntity <Void> dailyTickerSummary () {

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/previous/day")
    public ResponseEntity <Void> previousDayBar () {

        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
