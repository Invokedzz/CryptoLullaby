package org.cryptolullaby.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    InterestName(String label) {

        this.label = label;

    }

    public String getLabel () {

        return label;

    }

}
