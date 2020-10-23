package com.erzhiqian.team.application;

import com.erzhiqian.team.application.dto.NewTeam;
import com.erzhiqian.team.domain.exceptions.ErrorCode;
import com.erzhiqian.team.domain.team.Team;
import com.erzhiqian.team.domain.team.TeamRepository;
import org.springframework.stereotype.Service;

import static com.erzhiqian.team.domain.exceptions.DomainPreCondition.when;
import static com.erzhiqian.team.domain.exceptions.ErrorCode.TEAM_ALREADY_EXISTS;

@Service
public class TeamService {

    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void createTeam(NewTeam newTeam) {
        Team team = new Team(newTeam.getName());
        when(teamRepository.existsByName(team.getName()))
                .thenEntityAlreadyExists(TEAM_ALREADY_EXISTS, 
                        "Error creating team named '" + team.getName() + "'");
        teamRepository.save(team);
    }
}
