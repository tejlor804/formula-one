package hu.bredex.formula.one.team.controller;

import hu.bredex.formula.one.team.api.Team;
import hu.bredex.formula.one.team.api.TeamInput;
import hu.bredex.formula.one.team.api.TeamService;
import hu.bredex.formula.one.team.view.TeamView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    @CrossOrigin
    public List<TeamView> getTeams() {
        return teamService.list();
    }

    @PostMapping("/teams")
    @CrossOrigin
    public Team createTeam(@RequestBody @Valid TeamInput input){
        return teamService.create(input);
    }

    @PatchMapping("/teams/{id}")
    @CrossOrigin
    public Team updateTeam(@PathVariable(value = "id") Long id, @RequestBody @Valid TeamInput input){
        return teamService.update(input, id);
    }

    @DeleteMapping("/teams/{id}")
    @CrossOrigin
    public Boolean deleteTeam(@PathVariable(value = "id") Long id){
        return teamService.delete(id);
    }

}
