package com.erzhiqian.team.infrastructure.adapt;

import com.erzhiqian.team.domain.team.Team;
import com.erzhiqian.team.domain.team.TeamRepository;
import com.erzhiqian.team.infrastructure.persistence.MongoDbTeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamRepositoryAdapt implements TeamRepository {
    
    private MongoDbTeamRepository repository;

    public TeamRepositoryAdapt(MongoDbTeamRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public void save(Team team) {
        repository.save(team);
    }
}
