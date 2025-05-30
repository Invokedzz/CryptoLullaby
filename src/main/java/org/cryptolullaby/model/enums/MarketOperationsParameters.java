package org.cryptolullaby.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MarketOperationsParameters {

    CRYPTO("crypto"),

    GLOBAL("global"),

    TRADE("trade"),

    BBO("bbo"),

    NBBO("nbbo"),

    US("us");

    private final String label;

    MarketOperationsParameters (String label) {

        this.label = label;

    }

    @JsonValue
    public String getLabel () {

        return label;

    }


}
