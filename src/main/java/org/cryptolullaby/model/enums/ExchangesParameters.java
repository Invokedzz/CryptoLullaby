package org.cryptolullaby.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExchangesParameters {

    CRYPTO("crypto"),

    GLOBAL("global"),

    US("us");

    private final String label;

    ExchangesParameters (String label) {

        this.label = label;

    }

    @JsonValue
    public String getLabel () {

        return label;

    }


}
