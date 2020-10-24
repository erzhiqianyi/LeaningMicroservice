package com.erzhiqian.team.infrastructure.persistence;

import com.erzhiqian.team.domain.team.Team;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class MongoDbTeamRepository {
    private static final String TEAMS_COLLECTION = "teams";

    private MongoTemplate mongo;

    public MongoDbTeamRepository(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    public boolean existsByName(String name) {
        return mongo.exists(query(where("_id").is(name)), TEAMS_COLLECTION);
    }

    public void save(Team team) {
        mongo.save(team, TEAMS_COLLECTION);
    }

    public Team findById(String name) {
        return mongo.findById(name, Team.class, TEAMS_COLLECTION);
    }

    public List<Team> findAll() {
        return mongo.findAll(Team.class, TEAMS_COLLECTION);
    }
}
