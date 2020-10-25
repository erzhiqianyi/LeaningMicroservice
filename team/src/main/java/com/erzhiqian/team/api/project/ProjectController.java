package com.erzhiqian.team.api.project;


import com.erzhiqian.team.application.dto.project.*;
import com.erzhiqian.team.application.project.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

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

    @ResponseStatus(OK)
    @GetMapping("/{projectIdentifier}")
    public ExistingProject getProject(@PathVariable String projectIdentifier) {
        return projectService.getProject(projectIdentifier);
    }


    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{projectIdentifier}")
    public void updateProject(@PathVariable String projectIdentifier, @RequestBody UpdatedProject updatedProject) {
        projectService.updateProject(projectIdentifier, updatedProject);
    }


    @ResponseStatus(NO_CONTENT)
    @PatchMapping("/{projectIdentifier}/started")
    public void startProject(@PathVariable String projectIdentifier) {
        projectService.startProject(projectIdentifier);
    }


    @ResponseStatus(NO_CONTENT)
    @PatchMapping("/{projectIdentifier}/ended")
    public void endProject(@PathVariable String projectIdentifier, @RequestBody ProjectEndingCondition endingCondition) {
        projectService.endProject(projectIdentifier, endingCondition);
    }
}
