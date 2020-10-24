package com.erzhiqian.team.api;

import com.erzhiqian.team.application.TeamService;
import com.erzhiqian.team.application.dto.NewTeam;
import com.erzhiqian.team.application.dto.TeamMember;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

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

}
