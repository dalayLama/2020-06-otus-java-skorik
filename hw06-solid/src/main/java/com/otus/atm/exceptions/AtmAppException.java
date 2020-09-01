package com.otus.atm.exceptions;

public class AtmAppException extends RuntimeException {

    public AtmAppException() {
    }

    public AtmAppException(String message) {
        super(message);
    }

    public AtmAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AtmAppException(Throwable cause) {
        super(cause);
    }

    public AtmAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
