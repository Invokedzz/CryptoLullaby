package org.cryptolullaby.orchestration;

import org.cryptolullaby.infra.client.TreasuryYieldsClient;
import org.cryptolullaby.model.dto.polygon.treasury.TreasuryYieldsDTO;
import org.cryptolullaby.orchestration.usecases.polygon.TreasuryYieldsUseCase;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TreasuryYieldsOrchestrationFacade {

    private final TreasuryYieldsUseCase treasuryYieldsUseCase;

    public TreasuryYieldsOrchestrationFacade (TreasuryYieldsUseCase treasuryYieldsUseCase) {

        this.treasuryYieldsUseCase = treasuryYieldsUseCase;

    }

    public TreasuryYieldsDTO getTreasuryYields (String date, Map<String, String> params) {

        return treasuryYieldsUseCase.getTreasuryYields(date, params);

    }

}
