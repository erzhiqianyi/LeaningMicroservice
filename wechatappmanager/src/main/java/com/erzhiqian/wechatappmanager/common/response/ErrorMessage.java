package com.erzhiqian.wechatappmanager.common.response;

public class ErrorMessage {
    private Integer errorCode;

    private String message;


    public ErrorMessage(ErrorCode errorCode,String message) {
        this.errorCode = errorCode.errorCode();
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }


}
