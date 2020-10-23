package com.erzhiqian.team.infrastructure.error;

import com.erzhiqian.team.domain.exceptions.ErrorCode;

public class ErrorMessage {
    private Integer errorCode;

    private String message;
    
    public ErrorMessage(ErrorCode errorCode,String message) {
        this.errorCode = errorCode.getCode();
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
