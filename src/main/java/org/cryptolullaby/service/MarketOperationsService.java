package org.cryptolullaby.service;

import feign.FeignException;
import org.cryptolullaby.exception.ResourceNotFoundException;
import org.cryptolullaby.exception.UnauthorizedRequestException;
import org.cryptolullaby.infra.client.MarketOperationsClient;
import org.springframework.stereotype.Service;

@Service
public class MarketOperationsService {

    private final MarketOperationsClient marketOperationsClient;

    public MarketOperationsService (MarketOperationsClient marketOperationsClient) {

        this.marketOperationsClient = marketOperationsClient;

    }

    public void getMarketExchanges () {

        try {

            marketOperationsClient.getMarketExchanges();

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

    public void getUpcomingMarketHolidays () {

        try {

            marketOperationsClient.getUpcomingMarketHolidays();

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

    public void getCurrentTradingStatus () {

        try {

            marketOperationsClient.getCurrentTradingStatus();

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

}
