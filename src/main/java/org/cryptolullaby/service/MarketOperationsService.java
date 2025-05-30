package org.cryptolullaby.service;

import org.cryptolullaby.model.dto.market.ConditionsCodeDTO;
import org.cryptolullaby.model.dto.market.MarketExchangeDTO;
import org.cryptolullaby.model.dto.market.MarketHolidaysDTO;
import org.cryptolullaby.model.dto.market.TradingStatusDTO;

import java.util.List;
import java.util.Map;

public interface MarketOperationsService {

    MarketExchangeDTO getMarketExchanges (Map<String, String> params);

    List <MarketHolidaysDTO> getUpcomingMarketHolidays ();

    TradingStatusDTO getCurrentTradingStatus ();

    ConditionsCodeDTO getConditionsCode (String sip, Map <String, String> params);

}
