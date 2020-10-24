package com.erzhiqian.team.domain.value;

public enum  JobPosition {
    DEVELOPER,
    SCRUM_MASTER,
    PRODUCT_OWNER,
    INVALID;

    public boolean isInvalid() {
        return this == INVALID;
    }
}
