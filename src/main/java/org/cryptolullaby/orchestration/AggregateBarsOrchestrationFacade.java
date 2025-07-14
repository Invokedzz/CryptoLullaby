package org.cryptolullaby.orchestration;

import org.cryptolullaby.model.dto.polygon.bars.PreviousDayBarDTO;
import org.cryptolullaby.model.dto.polygon.bars.market.DailyMarketSummaryDTO;
import org.cryptolullaby.model.dto.polygon.bars.ticker.DailyTickerDTO;
import org.cryptolullaby.orchestration.usecases.polygon.AggregateBarsUseCase;
import org.springframework.stereotype.Service;

@Service
public class AggregateBarsOrchestrationFacade {

    private final AggregateBarsUseCase aggregateBarsUseCase;

    public AggregateBarsOrchestrationFacade (AggregateBarsUseCase aggregateBarsUseCase) {

        this.aggregateBarsUseCase = aggregateBarsUseCase;

    }

    public DailyMarketSummaryDTO getDailyMarketSummary (String date) {

        return aggregateBarsUseCase.getDailyMarketSummary(date);

    }

    public DailyTickerDTO getDailyTickerSummary (String from, String to, String date) {

        return aggregateBarsUseCase.getDailyTickerSummary(from, to, date);

    }

    public PreviousDayBarDTO getPreviousDayBar (String cryptoTicker) {

        return aggregateBarsUseCase.getPreviousDayBar(cryptoTicker);

    }

}
