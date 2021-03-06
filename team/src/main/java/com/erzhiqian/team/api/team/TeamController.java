package com.erzhiqian.team.api.team;

import com.erzhiqian.team.application.team.TeamService;
import com.erzhiqian.team.application.dto.team.ExistingTeam;
import com.erzhiqian.team.application.dto.team.NewTeam;
import com.erzhiqian.team.application.dto.team.TeamMember;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/teams")
public class TeamController {

   private TeamService teamService;

   public TeamController(TeamService teamService) {
      this.teamService = teamService;
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public void createTeam(@RequestBody NewTeam newTeam){
       teamService.createTeam(newTeam);
   }

   @ResponseStatus(CREATED)
   @PostMapping("/{teamName}/members")
   public void addMemberToTeam(@PathVariable String teamName, @RequestBody TeamMember teamMember) {
      teamService.addMemberToTeam(teamName, teamMember);
   }

   @ResponseStatus(OK)
   @GetMapping
   public List<ExistingTeam> getTeams() {
      return teamService.getTeams();
   }

}
