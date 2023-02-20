package hu.bredex.formula.one.team.api;

import hu.bredex.formula.one.team.view.TeamView;

import jakarta.validation.Valid;
import java.util.List;

public interface TeamService {

    Team create(@Valid TeamInput input);
    Team update(@Valid TeamInput input, Long id);
    List<TeamView> list();

    Boolean delete(Long id);

//    Boolean delete(@TeamExists Long id);
}
