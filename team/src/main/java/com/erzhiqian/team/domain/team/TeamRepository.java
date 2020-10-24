package com.erzhiqian.team.domain.team;

import java.util.List;

public interface TeamRepository {

    boolean existsByName(String name);

    Team findByName(String name);

    void save(Team team);
    
    List<Team> getTeams();
}
