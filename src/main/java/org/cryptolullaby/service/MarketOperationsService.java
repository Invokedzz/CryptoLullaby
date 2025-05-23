package org.cryptolullaby.service;

import feign.FeignException;
import org.cryptolullaby.exception.ResourceNotFoundException;
import org.cryptolullaby.exception.UnauthorizedRequestException;
import org.cryptolullaby.infra.client.MarketOperationsClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MarketOperationsService {

    private final MarketOperationsClient marketOperationsClient;

    public MarketOperationsService (MarketOperationsClient marketOperationsClient) {

        this.marketOperationsClient = marketOperationsClient;

    }

    public void getMarketExchanges (@RequestParam String asset_class, @RequestParam String locale) {

        try {

            marketOperationsClient.getMarketExchanges(asset_class, locale);

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
