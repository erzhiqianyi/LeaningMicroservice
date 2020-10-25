package com.erzhiqian.team.domain.value.project;

public enum Status {
    TO_DO,
    IN_PROGRESS,
    DONE,
    INVALID;

    public boolean isInvalid() {
        return this == INVALID;
    }

    public boolean isAtLeastStarted() {
        return   !isInvalid() &&  this != TO_DO;
    }

    public boolean isAtInProgress() {
        return this == IN_PROGRESS;
    }

    public boolean isNotStarted() {
        return this == TO_DO;
    }


    public boolean isDone() {
        return this == DONE;
    }
    
}
