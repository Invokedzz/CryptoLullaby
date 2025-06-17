package org.cryptolullaby.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.cryptolullaby.validation.DateFormatValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateFormatValidator.class)
public @interface DateFormat {

        String message() default "Enter a valid date!";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

}
