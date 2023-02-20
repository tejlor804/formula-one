package hu.bredex.formula.one.team.repository;

import hu.bredex.formula.one.team.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

}
