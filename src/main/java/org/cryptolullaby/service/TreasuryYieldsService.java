package org.cryptolullaby.service;

import org.cryptolullaby.infra.client.TreasuryYieldsClient;
import org.springframework.stereotype.Service;

@Service
public class TreasuryYieldsService {

    private final TreasuryYieldsClient treasuryYieldsClient;

    public TreasuryYieldsService(TreasuryYieldsClient treasuryYieldsClient) {

        this.treasuryYieldsClient = treasuryYieldsClient;

    }

    public void getTreasuryYields () {}

}
