package org.cryptolullaby.model.dto;

import java.util.List;

public record TradingStatusDTO (

        List <CurrenciesDTO> currencies,

        List <USStockExchangesDTO> exchanges,

        String market,

        String serverTime

) {}
