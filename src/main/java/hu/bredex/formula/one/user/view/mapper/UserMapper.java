package hu.bredex.formula.one.user.view.mapper;

import hu.bredex.formula.one.team.api.Team;
import hu.bredex.formula.one.team.entity.TeamEntity;
import hu.bredex.formula.one.user.api.User;
import hu.bredex.formula.one.user.entity.UserEntity;
import hu.bredex.formula.one.user.view.UserView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface UserMapper extends Function<UserEntity, User> {



    UserView toView(UserEntity userEntity);


    @Override
    default User apply(UserEntity entity) {
        return this.toView(entity);
    };
}
