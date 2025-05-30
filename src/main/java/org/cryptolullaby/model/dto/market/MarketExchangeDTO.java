package org.cryptolullaby.model.dto.market;

import java.util.List;

public record MarketExchangeDTO (

        List <ExchangeResultsDTO> results,

        String status

) {}
