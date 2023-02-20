package hu.bredex.formula.one.team.service;

import hu.bredex.formula.one.team.api.Team;
import hu.bredex.formula.one.team.api.TeamInput;
import hu.bredex.formula.one.team.api.TeamService;
import hu.bredex.formula.one.team.entity.TeamEntity;
import hu.bredex.formula.one.team.repository.TeamRepository;
import hu.bredex.formula.one.team.view.TeamView;
import hu.bredex.formula.one.team.view.mapper.TeamMapper;
import hu.bredex.formula.one.user.entity.UserEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "team")
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    CacheManager cacheManager;

    @PostConstruct
    public void init() {
        TeamEntity ferrari = TeamEntity.builder()
                .name("Ferrari")
                .wonWorldChampionships(16)
                .paidEntryFee(true)
                .foundingYear(1950)
                .build();
        TeamEntity mercedes = TeamEntity.builder()
                .name("Mercedes")
                .wonWorldChampionships(8)
                .paidEntryFee(false)
                .foundingYear(1966)
                .build();
        TeamEntity redBull = TeamEntity.builder()
                .name("Red Bull")
                .wonWorldChampionships(5)
                .paidEntryFee(true)
                .foundingYear(2005)
                .build();
        teamRepository.saveAll(List.of(ferrari, redBull, mercedes));
    }

    public Team create(TeamInput input) {

    TeamEntity entity = TeamEntity.builder()
            .name(input.getName())
            .foundingYear(input.getFoundingYear())
            .wonWorldChampionships(input.getWonWorldChampionships())
            .paidEntryFee(input.getPaidEntryFee())
            .build();
    teamRepository.save(entity);
    cacheManager.getCache("team").clear();
    return teamMapper.toView(entity);

    }

    public Team update(TeamInput input, Long id) {
        TeamEntity entity = teamRepository.getReferenceById(id);
        if(input.getName() != null) {
            entity.setName(input.getName());
        }
        if(input.getFoundingYear() != null) {
            entity.setFoundingYear(input.getFoundingYear());
        }
        if(input.getPaidEntryFee() != null) {
            entity.setPaidEntryFee(input.getPaidEntryFee());
        }
        if(input.getWonWorldChampionships() != null) {
            entity.setWonWorldChampionships(input.getWonWorldChampionships());
        }
        cacheManager.getCache("team").clear();
        teamRepository.save(entity);
        return teamMapper.toView(entity);

    }

    public Boolean delete(Long id) {

        teamRepository.deleteById(id);
        cacheManager.getCache("team").clear();
        return true;

    }

    @Cacheable(value = "team")
    public List<TeamView> list() {
        return teamRepository.findAll().stream().map(team -> teamMapper.toView(team)).toList();
    }
}
