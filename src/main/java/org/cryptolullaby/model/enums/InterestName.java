package org.cryptolullaby.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum InterestName {

    BLOCKCHAIN("Blockchain"),

    CRYPTOCURRENCY("Cryptocurrency"),

    CRYPTO_EXCHANGE("CryptoExchange"),

    DIGITAL_CURRENCY("DigitalCurrency"),

    DIGITAL_WALLET("DigitalWallet"),

    FIAT_CURRENCY("FiatCurrency"),

    METAVERSE("Metaverse"),

    NFT("NFT"),

    STABLECOIN("StableCoin"),

    VIRTUAL_CURRENCY("VirtualCurrency"),

    NONE("None");

    private final String label;

    InterestName (String label) {

        this.label = label;

    }

    @JsonValue
    public String getLabel () {

        return label;

    }

    @JsonCreator
    public static InterestName fromLabel (String label) {

        for (InterestName interestName : InterestName.values()) {

            if (interestName.label.equalsIgnoreCase(label)) {

                return interestName;

            }

        }

        throw new IllegalArgumentException("Invalid interest name: " + label);

    }

}
