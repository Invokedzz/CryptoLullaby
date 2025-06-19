package org.cryptolullaby.orchestration.usecases.polygon;

import feign.FeignException;
import org.cryptolullaby.exception.ResourceNotFoundException;
import org.cryptolullaby.exception.UnauthorizedRequestException;
import org.cryptolullaby.infra.client.MarketOperationsClient;
import org.cryptolullaby.model.dto.polygon.ConditionsCodeDTO;
import org.cryptolullaby.model.dto.polygon.MarketExchangeDTO;
import org.cryptolullaby.model.dto.polygon.MarketHolidaysDTO;
import org.cryptolullaby.model.dto.polygon.TradingStatusDTO;
import org.cryptolullaby.model.enums.MarketOperationsParameters;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MarketOperationsUseCase {

    private final MarketOperationsClient marketOperationsClient;

    private static final String ASSET_CLASS = MarketOperationsParameters.CRYPTO.getLabel();

    private static final String LOCALE = MarketOperationsParameters.GLOBAL.getLabel();

    private static final String DATA_TYPE = MarketOperationsParameters.TRADE.getLabel();

    public MarketOperationsUseCase (MarketOperationsClient marketOperationsClient) {

        this.marketOperationsClient = marketOperationsClient;

    }

    public MarketExchangeDTO getMarketExchanges (Map<String, String> params) {

        try {

            setupExchangesParams(params);

            return marketOperationsClient.getMarketExchanges(params);

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

    public List<MarketHolidaysDTO> getUpcomingMarketHolidays () {

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

    public ConditionsCodeDTO getConditionsCode (String sip, Map <String, String> params) {

        try {

//            setupConditionsCodeParams(sip, params);

            return marketOperationsClient.getConditionsCode(params);

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

    private void setupExchangesParams (Map <String, String> params) {

        params.put("asset_class", ASSET_CLASS);

        params.put("locale", LOCALE);

    }

    private void setupConditionsCodeParams (Map <String, String> params) {

        params.put("asset_class", ASSET_CLASS);

        params.put("data_type", DATA_TYPE);

    }

}
