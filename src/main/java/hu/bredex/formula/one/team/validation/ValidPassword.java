package hu.bredex.formula.one.team.validation;

import hu.bredex.formula.one.team.validation.validator.PasswordValidator;
import hu.bredex.formula.one.team.validation.validator.UniqueUsernameValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Password does not match the criterias!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}