package hu.bredex.formula.one.user.api;

import hu.bredex.formula.one.team.validation.UniqueUsername;
import hu.bredex.formula.one.team.validation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Data
@Validated
public class UserInput implements Serializable {

    @NotBlank
    @UniqueUsername
    private String username;
    @NotBlank
    @ValidPassword
    private String password;
}
