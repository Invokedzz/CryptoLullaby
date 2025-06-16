package org.cryptolullaby.model.dto.polygon.treasury;

public record TreasuryResultsDTO (

        String date,

        double yield_1_month,

        double yield_1_year,

        double yield_2_year,

        double yield_10_year,

        double yield_20_year

) {}
