package hu.bredex.formula.one.user.view;


import hu.bredex.formula.one.user.api.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserView implements User {

    private Long id;
    private String username;
    private String password;
}
