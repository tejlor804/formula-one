package hu.bredex.formula.one.team.api;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public interface Team {

    Long getId();
    String getName();
    Integer getFoundingYear();
    Integer getWonWorldChampionships();
    Boolean getPaidEntryFee();

}
