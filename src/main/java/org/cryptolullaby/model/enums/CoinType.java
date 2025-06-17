package org.cryptolullaby.model.enums;

public enum CoinType {

    BTC("X:BTCUSD"),

    DOGE("X:DOGEUSD"),

    BCH("X:BCHUSD"),

    XLM("X:XLMUSD"),

    XRP("X:XRPUSD"),

    SHIB("X:SHIBUSD"),

    LTC("X:LTCUSD"),

    XMR("X:XMRUSD"),

    KAS("X:KASUSD"),

    SKY2("X:SKY2USD");

    private final String label;

    CoinType (String label) {

        this.label = label;

    }

    public String getLabel() {

        return label;

    }

}
