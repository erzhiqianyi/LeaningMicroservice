package com.erzhiqian.team.application.dto.team;

import lombok.Data;

@Data
public class TeamMember {
    
    private String firstName;
    
    private String lastName;
    
    private String jobPosition;

    public TeamMember() {
    }
    
}
