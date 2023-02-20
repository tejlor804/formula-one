package hu.bredex.formula.one.team.validation.validator;

import hu.bredex.formula.one.team.validation.UniqueUsername;
import hu.bredex.formula.one.team.validation.ValidPassword;
import hu.bredex.formula.one.user.entity.UserEntity;
import hu.bredex.formula.one.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements
        ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword password) {
    }

    @Override
    public boolean isValid(String password,
                           ConstraintValidatorContext cxt) {
        //handled by @NotBlank
        if (password == null) {
            return true;
        }
        Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

}