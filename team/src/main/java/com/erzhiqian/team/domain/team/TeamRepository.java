package com.erzhiqian.team.domain.team;

public interface TeamRepository {

    boolean existsByName(String name);

    Team findByName(String name);

    void save(Team team);


}
