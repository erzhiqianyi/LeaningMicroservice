package com.erzhiqian.team.infrastructure.adapt;

import com.erzhiqian.team.domain.project.Project;
import com.erzhiqian.team.domain.project.ProjectRepository;
import com.erzhiqian.team.infrastructure.persistence.MongoDbProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class ProjectRepositoryAdapt implements ProjectRepository {

    private MongoDbProjectRepository repository;

    public ProjectRepositoryAdapt(MongoDbProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Project project) {
        repository.save(project);
    }

    @Override
    public List<Project> getProjects() {
        return repository.findAll();
    }

    @Override
    public Project getProject(String projectIdentifier) {
        if (isBlank(projectIdentifier)){
            return null;
        }
        return  repository.findById(projectIdentifier);
    }
}
