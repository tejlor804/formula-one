package hu.bredex.formula.one.team.validation.validator;

import hu.bredex.formula.one.team.validation.ValidFoundingYear;
import hu.bredex.formula.one.team.validation.ValidPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Year;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FoundingYearValidator implements
        ConstraintValidator<ValidFoundingYear, Integer> {

    @Override
    public void initialize(ValidFoundingYear password) {
    }

    @Override
    public boolean isValid(Integer foundingYear,
                           ConstraintValidatorContext cxt) {
        //handled by @NotBlank
        if (foundingYear == null) {
            return true;
        }
        Integer currentYear =  Year.now().getValue();
        return foundingYear >= 1950 && foundingYear <= currentYear;
    }

}