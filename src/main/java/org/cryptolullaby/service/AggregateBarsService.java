package org.cryptolullaby.service;

import feign.FeignException;
import org.cryptolullaby.exception.BadRequestException;
import org.cryptolullaby.exception.ResourceNotFoundException;
import org.cryptolullaby.exception.UnauthorizedRequestException;
import org.cryptolullaby.infra.client.AggregateBarsClient;
import org.cryptolullaby.model.dto.polygon.bars.PreviousDayBarDTO;
import org.cryptolullaby.model.dto.polygon.bars.market.DailyMarketSummaryDTO;
import org.cryptolullaby.model.dto.polygon.bars.ticker.DailyTickerDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AggregateBarsService {

    private final AggregateBarsClient aggregateBarsClient;

    public AggregateBarsService (AggregateBarsClient aggregateBarsClient) {

        this.aggregateBarsClient = aggregateBarsClient;

    }

    public DailyMarketSummaryDTO getDailyMarketSummary (String date) {

        try {

            return aggregateBarsClient.getDailyMarketSummary(date);

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.BadRequest ex) {

            throw new BadRequestException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

    public DailyTickerDTO getDailyTickerSummary (String from, String to, String date) {

        try {

            return aggregateBarsClient.getDailyTickerSummary(from, to, date);

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.BadRequest ex) {

            throw new BadRequestException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

    public PreviousDayBarDTO getPreviousDayBar (String cryptoTicker) {

        try {

            return aggregateBarsClient.getPreviousDayBar(cryptoTicker);

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.BadRequest ex) {

            throw new BadRequestException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

}
