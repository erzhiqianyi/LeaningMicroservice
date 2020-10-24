package com.erzhiqian.team.application.team;

import com.erzhiqian.team.application.dto.team.ExistingTeam;
import com.erzhiqian.team.application.dto.team.NewTeam;
import com.erzhiqian.team.application.dto.team.TeamMember;
import com.erzhiqian.team.application.utils.DtoMapper;
import com.erzhiqian.team.domain.team.Team;
import com.erzhiqian.team.domain.team.TeamRepository;
import com.erzhiqian.team.domain.value.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.erzhiqian.team.domain.exceptions.DomainPreCondition.when;
import static com.erzhiqian.team.domain.exceptions.ErrorCode.NONEXISTENT_TEAM;
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

    public void addMemberToTeam(String teamName, TeamMember teamMember) {
        Team team = teamRepository.findByName(teamName);
        when(null == team)
                .thenMissingEntity(NONEXISTENT_TEAM, "Error adding member to '" + teamName + "' team");
        Employee member = DtoMapper.mapToEmployee(teamMember);
        team.addMember(member);
        teamRepository.save(team);
    }

    public List<ExistingTeam> getTeams() {
        List<Team> teams = teamRepository.getTeams();
        return DtoMapper.mapToExistingTeam(teams);
    }
}
