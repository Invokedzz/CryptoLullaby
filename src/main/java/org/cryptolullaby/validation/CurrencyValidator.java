package org.cryptolullaby.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.cryptolullaby.model.enums.CurrencyType;
import org.cryptolullaby.validation.annotations.CurrencyValues;

public class CurrencyValidator implements ConstraintValidator <CurrencyValues, String> {

    private static final CurrencyType[] CURRENCIES = CurrencyType.values();

    @Override
    public boolean isValid (String s, ConstraintValidatorContext constraintValidatorContext) {

        for (CurrencyType currency : CURRENCIES) {

            if (s.equals(currency.name())) {

                return true;

            }

        }

        return false;

    }

}
