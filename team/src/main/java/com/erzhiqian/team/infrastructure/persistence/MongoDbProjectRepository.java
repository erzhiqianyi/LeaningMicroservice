package com.erzhiqian.team.infrastructure.persistence;

import com.erzhiqian.team.domain.project.Project;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MongoDbProjectRepository {

    private static final String PROJECT_COLLECTION = "projects";

    private MongoTemplate mongo;

    public MongoDbProjectRepository(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    public void save(Project project) {
        mongo.save(project, PROJECT_COLLECTION);
    }

    public List<Project> findAll() {
        return mongo.findAll(Project.class,PROJECT_COLLECTION);
    }
}
