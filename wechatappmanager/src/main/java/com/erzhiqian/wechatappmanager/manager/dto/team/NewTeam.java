package com.erzhiqian.wechatappmanager.manager.dto.team;

public class NewTeam {

    private String name;

    private String type;

    public NewTeam(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private NewTeam() {
    }
}
