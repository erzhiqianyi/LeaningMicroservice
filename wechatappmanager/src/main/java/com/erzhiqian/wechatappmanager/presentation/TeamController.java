package com.erzhiqian.wechatappmanager.presentation;


import com.erzhiqian.wechatappmanager.manager.dto.team.NewTeam;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @ResponseStatus(CREATED)
    @PostMapping
    void createTeam(@RequestBody NewTeam team) {

    }
}

