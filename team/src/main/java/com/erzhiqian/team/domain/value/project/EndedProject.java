package com.erzhiqian.team.domain.value.project;

import lombok.Data;

@Data
public class EndedProject {

    private String projectIdentifier;

    public EndedProject(String identifier) {
       this.projectIdentifier = identifier; 
    }
}
