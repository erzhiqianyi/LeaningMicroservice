package com.erzhiqian.team.infrastructure.adapt;

import com.erzhiqian.team.domain.project.Project;
import com.erzhiqian.team.domain.project.ProjectRepository;
import com.erzhiqian.team.infrastructure.persistence.MongoDbProjectRepository;
import org.springframework.stereotype.Service;

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
}
