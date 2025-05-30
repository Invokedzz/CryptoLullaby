package org.cryptolullaby.model.dto.market;

public record MarketHolidaysDTO (

        String close,

        String date,

        String exchange,

        String name,

        String open,

        String status

) {}
