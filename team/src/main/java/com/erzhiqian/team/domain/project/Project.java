package com.erzhiqian.team.domain.project;

import com.erzhiqian.team.domain.value.ProjectStatus;
import org.springframework.data.annotation.Id;

import static com.erzhiqian.team.domain.exceptions.DomainPreCondition.when;
import static com.erzhiqian.team.domain.exceptions.ErrorCode.EMPTY_PROJECT_IDENTIFIER;
import static com.erzhiqian.team.domain.exceptions.ErrorCode.EMPTY_PROJECT_NAME;
import static com.erzhiqian.team.domain.value.ProjectStatus.TODO;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class Project {

    @Id
    private String identifier;

    private String name;

    private ProjectStatus status;

    private String assignedTeam;

    public Project(String identifier, String name) {
        validateIdentifier(identifier,"Error creating '" + name + "' project");
        validateName(name,"Error creating '" + identifier  + "' project");
        this.identifier = identifier;
        this.name = name;
        this.status = TODO;
    }

    private void validateIdentifier(String identifier, String message) {
        when(isBlank(identifier))
                .thenInvalidEntity(EMPTY_PROJECT_IDENTIFIER,message);
    }
    private void validateName(String name, String message) {
        when(isBlank(name))
                .thenInvalidEntity(EMPTY_PROJECT_NAME,message);


    }


}
