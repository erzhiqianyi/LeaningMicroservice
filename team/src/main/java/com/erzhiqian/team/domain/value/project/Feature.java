package com.erzhiqian.team.domain.value.project;

import lombok.Getter;

import static com.erzhiqian.team.domain.value.project.Status.TODO;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Getter
public class Feature {

    private String name;

    private Status status;

    private Requirement requirement;

    public Feature(String name, Requirement requirement) {
        this.name = name;
        this.requirement = requirement;
        this.status = TODO;
    }


    public boolean isUnnamed() {
        return isBlank(name);
    }


    public boolean hasNoStatus() {
        return !hasStatus();
    }

    private boolean hasStatus() {
        return null != status;
    }

    public boolean hasNoRequirement() {
        return !hasRequirement();
    }

    public boolean hasInvalidRequirement() {
        return hasRequirement() && requirement.isInValid();
    }

    private boolean hasRequirement() {
        return null != requirement;
    }


    private Feature() {

    }
}
