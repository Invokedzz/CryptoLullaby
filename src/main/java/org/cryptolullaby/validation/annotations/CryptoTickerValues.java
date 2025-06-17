package org.cryptolullaby.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.cryptolullaby.validation.CryptoTickerValidator;
import org.cryptolullaby.validation.CurrencyValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CryptoTickerValidator.class)
public @interface CryptoTickerValues {

    String message() default "Enter a valid crypto ticker!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
