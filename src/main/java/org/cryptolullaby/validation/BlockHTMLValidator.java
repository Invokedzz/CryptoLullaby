package org.cryptolullaby.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.cryptolullaby.validation.annotations.BlockHTML;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

public class BlockHTMLValidator implements ConstraintValidator <BlockHTML, String> {

    private static final PolicyFactory POLICY_FACTORY = new HtmlPolicyBuilder().toFactory();

    @Override
    public boolean isValid (String s, ConstraintValidatorContext constraintValidatorContext) {

        var sanitizedValue = sanitize(s);

        return sanitizedValue.equals(s);

    }

    private String sanitize (String s) {

        var value = POLICY_FACTORY.sanitize(s);

        return Jsoup.clean(value, Safelist.basic());

    }

}
