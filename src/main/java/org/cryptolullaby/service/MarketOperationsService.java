package org.cryptolullaby.service;

import feign.FeignException;
import org.cryptolullaby.exception.ResourceNotFoundException;
import org.cryptolullaby.exception.UnauthorizedRequestException;
import org.cryptolullaby.infra.client.MarketOperationsClient;
import org.cryptolullaby.model.dto.ConditionsCodeDTO;
import org.cryptolullaby.model.dto.MarketExchangeDTO;
import org.cryptolullaby.model.dto.MarketHolidaysDTO;
import org.cryptolullaby.model.dto.TradingStatusDTO;
import org.cryptolullaby.model.enums.MarketOperationsParameters;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MarketOperationsService {

    private final MarketOperationsClient marketOperationsClient;

    public MarketOperationsService (MarketOperationsClient marketOperationsClient) {

        this.marketOperationsClient = marketOperationsClient;

    }

    public MarketExchangeDTO getMarketExchanges (Map <String, String> params) {

        try {

            setupExchangesParams(params);

            return marketOperationsClient.getMarketExchanges(params);

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

    public List <MarketHolidaysDTO> getUpcomingMarketHolidays () {

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

    public ConditionsCodeDTO getConditionsCode (Map <String, String> params) {

        try {

            setupConditionsCodeParams(params);

            return marketOperationsClient.getConditionsCode(params);

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

    private void setupExchangesParams (Map <String, String> params) {

        params.put("asset_class", MarketOperationsParameters.CRYPTO.getLabel());

        params.put("locale", MarketOperationsParameters.GLOBAL.getLabel());

    }

    private void setupConditionsCodeParams (Map <String, String> params) {

        params.put("asset_class", MarketOperationsParameters.CRYPTO.getLabel());

        params.put("data_type", MarketOperationsParameters.TRADE.getLabel());

    }

}
