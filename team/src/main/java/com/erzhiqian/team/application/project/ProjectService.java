package com.erzhiqian.team.application.project;

import com.erzhiqian.team.application.dto.project.NewProjectDraft;
import com.erzhiqian.team.domain.project.Project;
import com.erzhiqian.team.domain.project.ProjectFactory;
import com.erzhiqian.team.domain.project.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private ProjectFactory projectFactory;

    private ProjectRepository projectRepository;

    public ProjectService(ProjectFactory projectFactory,
                          ProjectRepository projectRepository) {
        this.projectFactory = projectFactory;
        this.projectRepository = projectRepository;
    }

    public void createProject(NewProjectDraft newProjectDraft) {
        Project project = projectFactory.createProjectDraft(newProjectDraft);
        projectRepository.save(project);
                
    }
}
