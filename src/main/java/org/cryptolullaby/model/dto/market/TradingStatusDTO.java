package org.cryptolullaby.model.dto.market;

public record TradingStatusDTO (

        CurrenciesDTO currencies,

        USStockExchangesDTO exchanges,

        String market,

        String serverTime

) {}
