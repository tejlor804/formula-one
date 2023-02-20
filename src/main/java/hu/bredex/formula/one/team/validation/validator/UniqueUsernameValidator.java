package hu.bredex.formula.one.team.validation.validator;

import hu.bredex.formula.one.team.validation.UniqueUsername;
import hu.bredex.formula.one.user.entity.UserEntity;
import hu.bredex.formula.one.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueUsernameValidator implements
        ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UniqueUsername username) {
    }

    @Override
    public boolean isValid(String username,
                           ConstraintValidatorContext cxt) {
        //handled by @NotBlank
        if (username == null) {
            return true;
        }
        List<UserEntity> users = userRepository.findAll();
        return users.stream().noneMatch(userEntity -> userEntity.getUsername().equals(username));
    }

}