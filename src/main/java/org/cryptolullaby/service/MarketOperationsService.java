package org.cryptolullaby.service;

import feign.FeignException;
import org.cryptolullaby.exception.ResourceNotFoundException;
import org.cryptolullaby.exception.UnauthorizedRequestException;
import org.cryptolullaby.infra.client.MarketOperationsClient;
import org.cryptolullaby.model.dto.MarketExchangeDTO;
import org.cryptolullaby.model.dto.MarketHolidaysDTO;
import org.cryptolullaby.model.dto.TradingStatusDTO;
import org.springframework.stereotype.Service;

@Service
public class MarketOperationsService {

    private final MarketOperationsClient marketOperationsClient;

    public MarketOperationsService (MarketOperationsClient marketOperationsClient) {

        this.marketOperationsClient = marketOperationsClient;

    }

    public MarketExchangeDTO getMarketExchanges (String asset_class, String locale) {

        try {

            return marketOperationsClient.getMarketExchanges (asset_class, locale);

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

    public MarketHolidaysDTO getUpcomingMarketHolidays () {

        try {

            return marketOperationsClient.getUpcomingMarketHolidays();

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

    public TradingStatusDTO getCurrentTradingStatus () {

        try {

            return marketOperationsClient.getCurrentTradingStatus();

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

}
