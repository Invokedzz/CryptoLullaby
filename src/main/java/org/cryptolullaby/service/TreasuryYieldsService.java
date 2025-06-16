package org.cryptolullaby.service;

import feign.FeignException;
import org.cryptolullaby.exception.BadRequestException;
import org.cryptolullaby.exception.ResourceNotFoundException;
import org.cryptolullaby.exception.UnauthorizedRequestException;
import org.cryptolullaby.infra.client.TreasuryYieldsClient;
import org.cryptolullaby.model.dto.polygon.treasury.TreasuryYieldsDTO;
import org.springframework.stereotype.Service;

@Service
public class TreasuryYieldsService {

    private final TreasuryYieldsClient treasuryYieldsClient;

    public TreasuryYieldsService(TreasuryYieldsClient treasuryYieldsClient) {

        this.treasuryYieldsClient = treasuryYieldsClient;

    }

    public TreasuryYieldsDTO getTreasuryYields () {

        try {

            return treasuryYieldsClient.getTreasuryYields();

        } catch (FeignException.NotFound ex) {

            throw new ResourceNotFoundException(ex.getMessage());

        } catch (FeignException.BadRequest ex) {

            throw new BadRequestException(ex.getMessage());

        } catch (FeignException.Unauthorized ex) {

            throw new UnauthorizedRequestException(ex.getMessage());

        }

    }

}
