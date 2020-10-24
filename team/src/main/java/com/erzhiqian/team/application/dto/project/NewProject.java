package com.erzhiqian.team.application.dto.project;

import lombok.Data;

import java.util.List;

@Data
public class NewProject {

    private String name;

    private List<NewFeature> features;



}
