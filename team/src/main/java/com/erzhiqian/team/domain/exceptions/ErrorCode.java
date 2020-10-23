package com.erzhiqian.team.domain.exceptions;

public enum  ErrorCode {

    UNEXPECTED_ERROR(-1),
    TEAM_ALREADY_EXISTS(40001),
    EMPTY_TEAM_NAME(40002)
    ;

    private Integer code;

    ErrorCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
