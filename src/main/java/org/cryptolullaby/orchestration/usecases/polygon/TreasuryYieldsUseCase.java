package org.cryptolullaby.orchestration.usecases.polygon;

import feign.FeignException;
import org.cryptolullaby.exception.BadRequestException;
import org.cryptolullaby.exception.ResourceNotFoundException;
import org.cryptolullaby.exception.UnauthorizedRequestException;
import org.cryptolullaby.infra.client.TreasuryYieldsClient;
import org.cryptolullaby.model.dto.polygon.treasury.TreasuryYieldsDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TreasuryYieldsUseCase {

    private final TreasuryYieldsClient treasuryYieldsClient;

    private static final int DEFAULT_LIMIT_REQUEST = 50;

    private static final String DATE_SORT = "date.desc";

    public TreasuryYieldsUseCase (TreasuryYieldsClient treasuryYieldsClient) {

        this.treasuryYieldsClient = treasuryYieldsClient;

    }

    public TreasuryYieldsDTO getTreasuryYields (String date, Map<String, String> params) {

        try {

            setupTreasuryYieldsParams(params);

            return treasuryYieldsClient.getTreasuryYields(date, params);

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.BadRequest ex) {

            throw new BadRequestException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

    private void setupTreasuryYieldsParams (Map <String, String> params) {

        params.put("limit", String.valueOf(DEFAULT_LIMIT_REQUEST));

        params.put("sort", DATE_SORT);

    }

}
