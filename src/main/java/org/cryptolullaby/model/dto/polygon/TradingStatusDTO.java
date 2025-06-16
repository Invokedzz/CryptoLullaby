package org.cryptolullaby.model.dto.polygon;

public record TradingStatusDTO (

        CurrenciesDTO currencies,

        USStockExchangesDTO exchanges,

        String market,

        String serverTime

) {}
