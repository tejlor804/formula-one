package hu.bredex.formula.one.team.entity;


import hu.bredex.formula.one.team.validation.ValidFoundingYear;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column
    @Min(0)
    @ValidFoundingYear
    private Integer foundingYear;

    @Column
    private Integer wonWorldChampionships;

    @Column
    private Boolean paidEntryFee;


}
