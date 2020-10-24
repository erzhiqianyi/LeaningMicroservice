package com.erzhiqian.team.application.dto.project;

import lombok.Data;

import java.util.List;

@Data
public class ExistingProject {
    private String identifier;
    private String name;
    private String status;
    private String team;
    private List<ProjectFeature> features;

}
