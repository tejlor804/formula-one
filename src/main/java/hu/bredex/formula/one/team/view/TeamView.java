package hu.bredex.formula.one.team.view;


import hu.bredex.formula.one.team.api.Team;
import jakarta.persistence.Cacheable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamView implements Team {

    private Long id;
    private String name;
    private Integer foundingYear;
    private Integer wonWorldChampionships;
    private Boolean paidEntryFee;
}
