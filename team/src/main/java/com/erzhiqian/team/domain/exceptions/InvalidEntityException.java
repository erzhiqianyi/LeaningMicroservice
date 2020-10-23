package com.erzhiqian.team.domain.exceptions;

public class InvalidEntityException extends DomainException{
    InvalidEntityException(String message, ErrorCode code) {
        super(message, code);
    }
}
