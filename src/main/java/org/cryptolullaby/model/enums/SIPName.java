package org.cryptolullaby.model.enums;

import java.util.Arrays;

public enum SIPName {

    CTA,

    UTP,

    OPRA;

    public static boolean isSIPValid (String sip) {

        return Arrays
                .stream(SIPName.values())
                .anyMatch(e -> e.name().equalsIgnoreCase(sip));

    }

}
