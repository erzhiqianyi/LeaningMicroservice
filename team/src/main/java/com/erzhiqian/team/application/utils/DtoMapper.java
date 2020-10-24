package com.erzhiqian.team.application.utils;

import com.erzhiqian.team.application.dto.project.NewFeature;
import com.erzhiqian.team.application.dto.team.ExistingTeam;
import com.erzhiqian.team.application.dto.team.TeamMember;
import com.erzhiqian.team.domain.team.Team;
import com.erzhiqian.team.domain.value.project.Feature;
import com.erzhiqian.team.domain.value.project.Requirement;
import com.erzhiqian.team.domain.value.team.Employee;
import com.erzhiqian.team.domain.value.team.JobPosition;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class DtoMapper {

    private DtoMapper() {

    }

    public static Employee mapToEmployee(TeamMember teamMember) {
        JobPosition jobPosition = mapToJobPosition(teamMember.getJobPosition());
        return new Employee(teamMember.getFirstName(), teamMember.getLastName(), jobPosition);
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

    public static List<Feature> mapToFeatures(List<NewFeature> newFeatures) {
        return emptyIfNull(newFeatures).stream()
                .map(DtoMapper::mapToFeature)
                .collect(toList());
    }

    private static Feature mapToFeature(NewFeature newFeature) {
        if (newFeature == null) {
            return null;
        }
        Requirement requirement = mapToRequirement(newFeature.getRequirement());
        return new Feature(newFeature.getName(), requirement);
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


    private static Requirement mapToRequirement(String requirement) {
        if (isBlank(requirement)) {
            return null;
        }
        try {
            return Requirement.valueOf(requirement);
        } catch (Exception e) {
            return Requirement.INVALID;
        }

    }
}
