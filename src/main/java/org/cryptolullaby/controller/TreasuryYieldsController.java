package org.cryptolullaby.controller;

import org.cryptolullaby.model.dto.polygon.treasury.TreasuryYieldsDTO;
import org.cryptolullaby.service.TreasuryYieldsService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/domain/treasury/yields")
public class TreasuryYieldsController {

    private final TreasuryYieldsService treasuryYieldsService;

    public TreasuryYieldsController (TreasuryYieldsService treasuryYieldsService) {

        this.treasuryYieldsService = treasuryYieldsService;

    }

    @GetMapping
    public ResponseEntity <TreasuryYieldsDTO> treasuryYields (

            @RequestParam(required = false) String date,

            Map <String, String> params

    )

    {

        var treasuryFields = treasuryYieldsService.getTreasuryYields(date, params);

        return ResponseEntity.status(HttpStatus.OK).body(treasuryFields);

    }

}
