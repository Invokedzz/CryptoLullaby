package org.cryptolullaby.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.cryptolullaby.validation.annotations.LimitDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatValidator implements ConstraintValidator <LimitDateFormat, String> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final LocalDate MIN_DATE = LocalDate.of(2023, 7, 1);

    @Override
    public boolean isValid (String s, ConstraintValidatorContext constraintValidatorContext) {

        try {

            LocalDate date = LocalDate.parse(s, DATE_FORMATTER);

            return date.isAfter(MIN_DATE);

        } catch (DateTimeParseException ex) {

            return false;

        }

    }

}
