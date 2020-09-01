package com.otus.atm.exceptions;

public class PickingUpException extends AtmAppException {

    public PickingUpException() {
    }

    public PickingUpException(String message) {
        super(message);
    }

    public PickingUpException(String message, Throwable cause) {
        super(message, cause);
    }

    public PickingUpException(Throwable cause) {
        super(cause);
    }

    public PickingUpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
