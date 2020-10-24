package com.erzhiqian.team.application.dto.project;

import lombok.Data;

import java.util.List;

@Data
public class UpdatedProject {
    
    private String name;
    
    private String team;
    
    private List<ProjectFeature> features;

}
