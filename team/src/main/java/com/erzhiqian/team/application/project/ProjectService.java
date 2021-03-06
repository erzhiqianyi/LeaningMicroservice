package com.erzhiqian.team.application.project;

import com.erzhiqian.team.application.dto.project.*;
import com.erzhiqian.team.domain.project.FeatureChecker;
import com.erzhiqian.team.domain.project.Project;
import com.erzhiqian.team.domain.project.ProjectFactory;
import com.erzhiqian.team.domain.project.ProjectRepository;
import com.erzhiqian.team.domain.services.ProjectTeamAssigner;
import com.erzhiqian.team.domain.team.Team;
import com.erzhiqian.team.domain.team.TeamRepository;
import com.erzhiqian.team.domain.value.project.EndedProject;
import com.erzhiqian.team.domain.value.project.Feature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.erzhiqian.team.application.utils.DtoMapper.*;
import static com.erzhiqian.team.domain.exceptions.DomainPreCondition.when;
import static com.erzhiqian.team.domain.exceptions.ErrorCode.NONEXISTENT_PROJECT;
import static com.erzhiqian.team.domain.project.FeatureChecker.resolveFeatureChecker;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class ProjectService {

    private ProjectFactory projectFactory;

    private ProjectRepository projectRepository;

    private TeamRepository  teamRepository;

    private ProjectTeamAssigner  projectTeamAssigner;


    private ApplicationEventPublisher eventPublisher;

    public ProjectService(ProjectFactory projectFactory,
                          ProjectRepository projectRepository,
                          TeamRepository teamRepository,
                          ProjectTeamAssigner projectTeamAssigner,
                          ApplicationEventPublisher eventPublisher) {
        this.projectFactory = projectFactory;
        this.projectRepository = projectRepository;
        this.teamRepository = teamRepository;
        this.projectTeamAssigner = projectTeamAssigner;
        this.eventPublisher = eventPublisher;
    }

    public void createProjectDraft(NewProjectDraft newProjectDraft) {
        Project project = projectFactory.createProjectDraft(newProjectDraft);
        projectRepository.save(project);

    }

    public void createFullProject(NewProject newProject) {
        List<Feature> features = newFeatureMapToFeatures(newProject.getFeatures());
        Project project = projectFactory.createFullProject(newProject.getName(), features);
        projectRepository.save(project);
    }

    public List<ExistingProjectDraft> getProjects() {
        List<Project> projects = projectRepository.getProjects();
        return mapToExistingProjectDrafts(projects);
    }

    public ExistingProject getProject(String projectIdentifier) {
        when(isBlank(projectIdentifier)).
                thenMissingEntity(NONEXISTENT_PROJECT, "Error getting '" + projectIdentifier + "' project");
        Project project = projectRepository.getProject(projectIdentifier);
        when(null == project).
                thenMissingEntity(NONEXISTENT_PROJECT, "Error getting '" + projectIdentifier + "' project");
        return mapToExistingProject(project);
    }


    public void updateProject(String projectIdentifier, UpdatedProject updatedProject) {
        Project project = projectRepository.getProject(projectIdentifier);
        when(project == null)
                .thenMissingEntity(NONEXISTENT_PROJECT, "Error updating '" + projectIdentifier + "' project");
        List<Feature> features = projectFeatureMapToFeatures(updatedProject.getFeatures());
        project.rename(updatedProject.getName());
        project.updateFeatures(features);
        Team team = teamRepository.findByName(updatedProject.getTeam());
        projectTeamAssigner.assignTeamToProject(team, project);
        projectRepository.save(project);
        teamRepository.save(team);

    }


    public void startProject(String projectIdentifier) {
        Project project = projectRepository.getProject(projectIdentifier);
        when(project == null)
                .thenMissingEntity(NONEXISTENT_PROJECT, "Error starting '" + projectIdentifier + "' project");
        project.start();
        projectRepository.save(project);

    }

    public void endProject(String projectIdentifier, ProjectEndingCondition endingCondition) {
        Project project = projectRepository.getProject(projectIdentifier);
        when(project == null)
                .thenMissingEntity(NONEXISTENT_PROJECT, "Error ending '" + projectIdentifier + "' project");
        FeatureChecker featureChecker = resolveFeatureChecker(endingCondition.isOnlyNecessaryFeatureDone());
        EndedProject endedProject = project.end(featureChecker);
        if (project.hasAssignedTeam()) {
            Team assignedTeam = teamRepository.findByName(project.getAssignedTeam());
            assignedTeam.removeCurrentlyImplementedProject();
            teamRepository.save(assignedTeam);
        }
        projectRepository.save(project);
        eventPublisher.publishEvent(endedProject);
    }
}
