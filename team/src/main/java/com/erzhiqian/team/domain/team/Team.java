package com.erzhiqian.team.domain.team;

import org.springframework.data.annotation.Id;

import static com.erzhiqian.team.domain.exceptions.DomainPreCondition.when;
import static com.erzhiqian.team.domain.exceptions.ErrorCode.EMPTY_TEAM_NAME;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class Team {
    @Id
    private String name;

    private int currentlyImplementApps;


    public Team(String name) {
        validateName(name, "Error creating team.");
        this.name = name;
        currentlyImplementApps = 0;
    }

    private Team(){

    }

    private void validateName(String name, String message) {
        when(isBlank(name))
                .thenInvalidEntity(EMPTY_TEAM_NAME, message);
    }

    public String getName() {
        return name;
    }
}
