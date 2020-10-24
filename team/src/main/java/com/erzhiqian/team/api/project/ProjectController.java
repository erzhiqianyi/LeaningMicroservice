package com.erzhiqian.team.api.project;


import com.erzhiqian.team.application.dto.project.ExistingProjectDraft;
import com.erzhiqian.team.application.dto.project.NewProject;
import com.erzhiqian.team.application.dto.project.NewProjectDraft;
import com.erzhiqian.team.application.project.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ResponseStatus(CREATED)
    @PostMapping("/drafts")
    public void createProject(@RequestBody NewProjectDraft newProjectDraft) {
        projectService.createProjectDraft(newProjectDraft);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public void createProject(@RequestBody NewProject newProject) {
        projectService.createFullProject(newProject);
    }

    @ResponseStatus(OK)
    @GetMapping
    public List<ExistingProjectDraft> getProjects() {
        return projectService.getProjects();
    }

}
