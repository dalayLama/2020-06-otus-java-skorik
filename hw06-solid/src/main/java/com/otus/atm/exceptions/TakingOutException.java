package com.otus.atm.exceptions;

public class TakingOutException extends AtmAppException {

    public TakingOutException() {
    }

    public TakingOutException(String message) {
        super(message);
    }

    public TakingOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public TakingOutException(Throwable cause) {
        super(cause);
    }

    public TakingOutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
