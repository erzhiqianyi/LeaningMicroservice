package com.erzhiqian.team.application.utils;

import com.erzhiqian.team.application.dto.project.ExistingProject;
import com.erzhiqian.team.application.dto.project.ExistingProjectDraft;
import com.erzhiqian.team.application.dto.project.NewFeature;
import com.erzhiqian.team.application.dto.project.ProjectFeature;
import com.erzhiqian.team.application.dto.team.ExistingTeam;
import com.erzhiqian.team.application.dto.team.TeamMember;
import com.erzhiqian.team.domain.project.Project;
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

    public static List<ExistingProjectDraft> mapToExistingProjectDrafts(List<Project> projects) {
        return emptyIfNull(projects).stream()
                .map(DtoMapper::mapToExistingProjectDraft)
                .collect(toList());
    }


    public static ExistingProject mapToExistingProject(Project project) {
        ExistingProject existingProject = new ExistingProject();
        existingProject.setIdentifier(project.getIdentifier());
        existingProject.setName(project.getName());
        existingProject.setTeam(project.getAssignedTeam());
        existingProject.setStatus(project.getStatus().name());
        existingProject.setFeatures(mapToProjectFeatures(project.getFeatures()));
        return existingProject;
    }

    private static List<ProjectFeature> mapToProjectFeatures(List<Feature> features) {
        return emptyIfNull(features)
                .stream()
                .map(DtoMapper::mapToProjectFeature)
                .collect(toList());
    }


    private static ProjectFeature mapToProjectFeature(Feature feature) {
        ProjectFeature projectFeature = new ProjectFeature();
        projectFeature.setName(feature.getName());
        projectFeature.setRequirement(feature.getRequirement().name());
        projectFeature.setStatus(feature.getStatus().name());
        return projectFeature;
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

    private static ExistingProjectDraft mapToExistingProjectDraft(Project project) {
        ExistingProjectDraft existingProjectDraft = new ExistingProjectDraft();
        existingProjectDraft.setIdentifier(project.getIdentifier());
        existingProjectDraft.setName(project.getName());
        return existingProjectDraft;
    }

}
