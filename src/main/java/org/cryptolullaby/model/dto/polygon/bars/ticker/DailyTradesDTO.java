package org.cryptolullaby.model.dto.polygon.bars.ticker;

import java.math.BigDecimal;
import java.util.List;

public record DailyTradesDTO(

        List <Integer> c,

        String i,

        double p,

        double s,

        BigDecimal t,

        BigDecimal x

) {}
