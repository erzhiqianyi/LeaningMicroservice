package com.erzhiqian.team.domain.services;

import com.erzhiqian.team.domain.project.Project;
import com.erzhiqian.team.domain.team.Team;
import org.springframework.stereotype.Component;

@Component
public class ProjectTeamAssigner {

    public void assignTeamToProject(Team team, Project project) {
        project.assignTeam(team);
        if (team != null) {
            team.addCurrentlyImplementedProject();
        }
    }
}
