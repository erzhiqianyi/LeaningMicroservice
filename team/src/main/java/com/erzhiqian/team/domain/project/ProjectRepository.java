package com.erzhiqian.team.domain.project;

import java.util.List;

public interface ProjectRepository {
    void save(Project project);

    List<Project> getProjects();
}
