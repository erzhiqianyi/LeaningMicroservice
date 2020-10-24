package com.erzhiqian.team.domain.exceptions;

public enum  ErrorCode {

    UNEXPECTED_ERROR(-1),
    TEAM_ALREADY_EXISTS(40001),
    EMPTY_TEAM_NAME(40002),
    NONEXISTENT_TEAM(40003),
    EMPTY_MEMBER(40004),
    EMPTY_MEMBER_FIRST_NAME(40005),
    EMPTY_MEMBER_LAST_NAME(40006),
    EMPTY_MEMBER_JOB_POSITION(40007),
    INVALID_MEMBER_JOB_POSITION(40008),
    EMPTY_PROJECT_IDENTIFIER(40009),
    EMPTY_PROJECT_NAME(40010),
    EMPTY_FEATURE(40011),
    EMPTY_FEATURE_NAME(40012),
    EMPTY_FEATURE_STATUS(40013),
    EMPTY_FEATURE_REQUIREMENT(40014),
    INVALID_FEATURE_REQUIREMENT(40015),


    ;

    private Integer code;

    ErrorCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
