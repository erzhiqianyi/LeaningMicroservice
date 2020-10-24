package com.erzhiqian.team.application.project;

import com.erzhiqian.team.application.dto.project.NewProject;
import com.erzhiqian.team.application.dto.project.NewProjectDraft;
import com.erzhiqian.team.application.utils.DtoMapper;
import com.erzhiqian.team.domain.project.Project;
import com.erzhiqian.team.domain.project.ProjectFactory;
import com.erzhiqian.team.domain.project.ProjectRepository;
import com.erzhiqian.team.domain.value.project.Feature;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.erzhiqian.team.application.utils.DtoMapper.mapToFeatures;

@Service
public class ProjectService {

    private ProjectFactory projectFactory;

    private ProjectRepository projectRepository;

    public ProjectService(ProjectFactory projectFactory,
                          ProjectRepository projectRepository) {
        this.projectFactory = projectFactory;
        this.projectRepository = projectRepository;
    }

    public void createProjectDraft(NewProjectDraft newProjectDraft) {
        Project project = projectFactory.createProjectDraft(newProjectDraft);
        projectRepository.save(project);
                
    }

    public void createFullProject(NewProject newProject) {
        List<Feature> features = mapToFeatures(newProject.getFeatures());
        Project project = projectFactory.createFullProject(newProject.getName(),features);
        projectRepository.save(project);
    }
}
