package org.cryptolullaby.model.enums;

public enum InterestEnum {

    BLOCKCHAIN("Blockchain"),

    CRYPTOCURRENCY("Cryptocurrency"),

    CRYPTO_EXCHANGE("CryptoExchange"),

    DIGITAL_CURRENCY("DigitalCurrency"),

    DIGITAL_WALLET("DigitalWallet"),

    FIAT_CURRENCY("FiatCurrency"),

    METAVERSE("Metaverse"),

    NFT("NFT"),

    STABLECOIN("StableCoin"),

    VIRTUAL_CURRENCY("VirtualCurrency");

    private final String label;

    InterestEnum (String label) {

        this.label = label;

    }

    public String getLabel () {

        return label;

    }

}
