package com.erzhiqian.team.domain.project;

import com.erzhiqian.team.application.dto.project.NewProjectDraft;
import com.erzhiqian.team.domain.services.UniqueIdentifierGenerator;
import com.erzhiqian.team.domain.value.project.Feature;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectFactory {
    private UniqueIdentifierGenerator identifierGenerator;

    public ProjectFactory(UniqueIdentifierGenerator identifierGenerator) {
        this.identifierGenerator = identifierGenerator;
    }

    public Project createProjectDraft(NewProjectDraft newProjectDraft) {
        String identifier = identifierGenerator.generateUniqueIdentifier();
        return new Project(identifier,newProjectDraft.getName());
    }

    public Project createFullProject(String name, List<Feature> features) {
        String identifier = identifierGenerator.generateUniqueIdentifier();
        return new Project(identifier,name,features);
    }
}
