package org.cryptolullaby.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.cryptolullaby.validation.CoinValidator;
import org.cryptolullaby.validation.CurrencyValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CoinValidator.class)
public @interface CoinValues {

    String message() default "Enter a valid coin!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
