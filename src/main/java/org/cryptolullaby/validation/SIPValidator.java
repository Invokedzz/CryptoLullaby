package org.cryptolullaby.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.cryptolullaby.model.enums.SIPName;
import org.cryptolullaby.validation.annotations.SIPValues;

public class SIPValidator implements ConstraintValidator <SIPValues, String> {

    @Override
    public boolean isValid (String s, ConstraintValidatorContext constraintValidatorContext) {

        return SIPName.isSIPValid(s);

    }

}
