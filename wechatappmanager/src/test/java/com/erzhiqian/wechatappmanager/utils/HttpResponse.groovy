package com.erzhiqian.wechatappmanager.utils

import com.erzhiqian.wechatappmanager.common.response.ErrorCode
import com.erzhiqian.wechatappmanager.common.response.ErrorMessage
import org.springframework.http.HttpStatus

class HttpResponse<B> {

    private HttpStatus status
    private B body
    private ErrorMessage errorMessage

    protected HttpResponse(HttpStatus status, B body, ErrorMessage errorMessage) {
        this.status = status
        this.body = body
        this.errorMessage = errorMessage;
    }

    HttpStatus getStatus() {
        status
    }

    B getBody() {
        body
    }

    ErrorMessage getErrorMessage() {
        return errorMessage
    }
}
