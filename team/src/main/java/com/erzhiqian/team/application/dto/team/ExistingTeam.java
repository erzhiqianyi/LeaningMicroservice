package com.erzhiqian.team.application.dto.team;

import lombok.Data;

import java.util.List;

@Data
public class ExistingTeam {

    private String name;

    private int currentlyImplementedProjects;

    private boolean busy;

    private List<TeamMember> members;



}
