package org.cryptolullaby.model.dto;

public record TradingStatusDTO (

        CurrenciesDTO currencies,

        USStockExchangesDTO exchanges,

        String market,

        String serverTime

) {}
