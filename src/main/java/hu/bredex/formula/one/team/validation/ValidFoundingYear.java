package hu.bredex.formula.one.team.validation;

import hu.bredex.formula.one.team.validation.validator.FoundingYearValidator;
import hu.bredex.formula.one.team.validation.validator.UniqueUsernameValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FoundingYearValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFoundingYear {
    String message() default "Founding year is not valid!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}