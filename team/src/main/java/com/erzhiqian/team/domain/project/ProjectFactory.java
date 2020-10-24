package com.erzhiqian.team.domain.project;

import com.erzhiqian.team.application.dto.project.NewProjectDraft;
import com.erzhiqian.team.domain.services.UniqueIdentifierGenerator;
import org.springframework.stereotype.Component;

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
}
