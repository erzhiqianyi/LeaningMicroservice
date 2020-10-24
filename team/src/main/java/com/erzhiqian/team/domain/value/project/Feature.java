package com.erzhiqian.team.domain.value.project;

import lombok.Getter;

import static com.erzhiqian.team.domain.value.project.Status.TO_DO;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Getter
public class Feature {

    private String name;

    private Status status;

    private Requirement requirement;

    public Feature(String name, Requirement requirement) {
        this(name, requirement, TO_DO);
    }

    public Feature(String name, Requirement requirement, Status status) {
        this.name = name;
        this.requirement = requirement;
        this.status = status;
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

    public boolean hasInvalidStatus() {
        return hasStatus() && status.isInvalid();
    }
}
