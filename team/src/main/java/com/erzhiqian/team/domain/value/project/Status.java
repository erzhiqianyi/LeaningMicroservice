package com.erzhiqian.team.domain.value.project;

public enum Status {
    TO_DO,
    IN_PROGRESS,
    DONE,
    INVALID;

    public boolean isInvalid() {
        return this == INVALID;
    }
}
