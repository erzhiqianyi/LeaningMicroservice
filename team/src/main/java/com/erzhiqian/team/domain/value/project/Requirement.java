package com.erzhiqian.team.domain.value.project;

public enum Requirement {
    OPTIONAL,
    RECOMMENDED,
    NECESSARY,
    INVALID;

    public boolean isInValid() {
        return this ==  INVALID;
    }

    public boolean isNecessary() {
        return this == NECESSARY;
    }
}
