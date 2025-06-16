package org.cryptolullaby.service;

import org.cryptolullaby.infra.client.AggregateBarsClient;
import org.springframework.stereotype.Service;

@Service
public class AggregateBarsService {

    private final AggregateBarsClient aggregateBarsClient;

    public AggregateBarsService (AggregateBarsClient aggregateBarsClient) {

        this.aggregateBarsClient = aggregateBarsClient;

    }

    public void getDailyMarketSummary () {}

    public void getDailyTickerSummary () {}

    public void getPreviousDayBar () {}

}
