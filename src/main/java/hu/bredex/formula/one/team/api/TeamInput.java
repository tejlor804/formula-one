package hu.bredex.formula.one.team.api;

import hu.bredex.formula.one.team.validation.ValidFoundingYear;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@Validated
public class TeamInput implements Serializable {

    @NotBlank
    private String name;

    @Min(0)
    @NotNull
    @ValidFoundingYear
    private Integer foundingYear;

    @Min(0)
    private Integer wonWorldChampionships;

    private Boolean paidEntryFee;
}
