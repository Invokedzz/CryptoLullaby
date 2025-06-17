package org.cryptolullaby.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.cryptolullaby.model.enums.Currency;
import org.cryptolullaby.validation.annotations.CurrencyValues;

public class CurrencyValidator implements ConstraintValidator <CurrencyValues, String> {

    private static final Currency [] CURRENCIES = Currency.values();

    @Override
    public boolean isValid (String s, ConstraintValidatorContext constraintValidatorContext) {

        for (Currency currency : CURRENCIES) {

            if (s.equals(currency.name())) {

                return true;

            }

        }

        return false;

    }

}
