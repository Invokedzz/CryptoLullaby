package org.cryptolullaby.model.dto.polygon;

import java.util.List;

public record MarketExchangeDTO (

        List <ExchangeResultsDTO> results,

        String status

) {}
