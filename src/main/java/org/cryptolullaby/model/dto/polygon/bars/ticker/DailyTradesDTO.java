package org.cryptolullaby.model.dto.polygon.bars.ticker;

import java.util.List;

public record DailyTradesDTO(

        List <Integer> c,

        String i,

        double p,

        double s,

        int t,

        int x

) {}
