package com.erzhiqian.team.domain.exceptions;

import java.util.function.Supplier;

public class DomainPreCondition {
    private boolean condition;

    private DomainPreCondition(boolean condition) {
        this.condition = condition;
    }

    public static DomainPreCondition when(boolean condition) {
        return new DomainPreCondition(condition);
    }

    public void thenInvalidEntity(ErrorCode code, String message) {
        thenThrow(() -> new InvalidEntityException(message, code));
    }

    public void thenEntityAlreadyExists(ErrorCode code, String message) {
        thenThrow(() -> new EntityAlreadyExistsException(message, code));
    }

    public void thenMissingEntity(ErrorCode code, String message) {
        thenThrow(() -> new MissingEntityException(message, code));
    }

    private void thenThrow(Supplier<DomainException> exceptionCreator) {
        if (condition) {
            throw exceptionCreator.get();
        }

    }
}
