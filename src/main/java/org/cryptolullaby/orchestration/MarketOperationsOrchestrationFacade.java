package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.polygon.ConditionsCodeDTO;
import org.cryptolullaby.model.dto.polygon.MarketExchangeDTO;
import org.cryptolullaby.model.dto.polygon.MarketHolidaysDTO;
import org.cryptolullaby.model.dto.polygon.TradingStatusDTO;
import org.cryptolullaby.orchestration.usecases.polygon.MarketOperationsUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MarketOperationsOrchestrationFacade {

    private final MarketOperationsUseCase marketOperationsUseCase;

    public MarketOperationsOrchestrationFacade (MarketOperationsUseCase marketOperationsUseCase) {

        this.marketOperationsUseCase = marketOperationsUseCase;

    }

    public MarketExchangeDTO getMarketExchanges (Map<String, String> params) {

        return marketOperationsUseCase.getMarketExchanges(params);

    }

    public List <MarketHolidaysDTO> getUpcomingMarketHolidays () {

        return marketOperationsUseCase.getUpcomingMarketHolidays();

    }

    public TradingStatusDTO getCurrentTradingStatus () {

        return marketOperationsUseCase.getCurrentTradingStatus();

    }

    public ConditionsCodeDTO getConditionsCode (Map <String, String> params) {

        return marketOperationsUseCase.getConditionsCode(params);

    }

}
