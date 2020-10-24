package com.erzhiqian.team.infrastructure.persistence;

import com.erzhiqian.team.domain.project.Project;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MongoDbProjectRepository {

    private static final String TEAMS_COLLECTION = "projects";

    private MongoTemplate mongo;

    public MongoDbProjectRepository(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    public void save(Project project) {
        mongo.save(project, TEAMS_COLLECTION);
    }
}
