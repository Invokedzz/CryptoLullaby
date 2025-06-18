package org.cryptolullaby.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.cryptolullaby.validation.BlockHTMLValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BlockHTMLValidator.class)
public @interface BlockHTML {

    String message() default "Please bruh, no HTML injection!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
