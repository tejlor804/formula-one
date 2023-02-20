package hu.bredex.formula.one.team.view.mapper;

import hu.bredex.formula.one.team.api.Team;
import hu.bredex.formula.one.team.entity.TeamEntity;
import hu.bredex.formula.one.team.view.TeamView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper{


    TeamView toView(TeamEntity teamEntity);

    TeamEntity apply(Team view);
}
