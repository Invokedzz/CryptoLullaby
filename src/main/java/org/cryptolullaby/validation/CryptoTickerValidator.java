package org.cryptolullaby.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.cryptolullaby.model.enums.CoinType;
import org.cryptolullaby.validation.annotations.CryptoTickerValues;

public class CryptoTickerValidator implements ConstraintValidator <CryptoTickerValues, String> {

    private static final CoinType [] COIN_TYPES = CoinType.values();

    @Override
    public boolean isValid (String s, ConstraintValidatorContext constraintValidatorContext) {

        for (CoinType coinType : COIN_TYPES) {

            if (s.equals(coinType.getLabel())) {

                return true;

            }

        }

        return false;

    }

}
