package com.erzhiqian.team.application.utils;

import com.erzhiqian.team.application.dto.team.ExistingTeam;
import com.erzhiqian.team.application.dto.team.TeamMember;
import com.erzhiqian.team.domain.team.Team;
import com.erzhiqian.team.domain.value.Employee;
import com.erzhiqian.team.domain.value.JobPosition;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class DtoMapper {

    private DtoMapper() {

    }

    public static Employee mapToEmployee(TeamMember teamMember) {
        JobPosition jobPosition = mapToJobPosition(teamMember.getJobPosition());
        return new Employee(teamMember.getFirstName(),teamMember.getLastName(),jobPosition);
    }

    private static JobPosition mapToJobPosition(String jobPosition) {
        if (isBlank(jobPosition)) {
            return null;
        }
        try {
            return JobPosition.valueOf(jobPosition);
        } catch (Exception e) {
            return JobPosition.INVALID;
        }


    }

    public static List<ExistingTeam> mapToExistingTeam(List<Team> teams) {
        return emptyIfNull(teams).stream()
                .map(DtoMapper::mapToExistingTeam)
                .collect(toList());
    }

    private static ExistingTeam mapToExistingTeam(Team team) {
        ExistingTeam existingTeam = new ExistingTeam();
        existingTeam.setName(team.getName());
        existingTeam.setBusy(team.isBusy());
        existingTeam.setCurrentlyImplementedProjects(team.getCurrentlyImplementApps());
        existingTeam.setMembers(team.getMembers().stream()
                .map(DtoMapper::mapToTeamMember)
                .collect(toList())
        );
        return existingTeam;
    }

    private static TeamMember mapToTeamMember(Employee employee) {
        TeamMember member = new TeamMember();
        member.setFirstName(employee.getFirstName());
        member.setLastName(employee.getLastName());
        member.setJobPosition(employee.getJobPosition().name());
        return member;
    }


}
