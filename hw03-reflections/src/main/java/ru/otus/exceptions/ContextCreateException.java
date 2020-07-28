package ru.otus.exceptions;

public class ContextCreateException extends Exception {

    public ContextCreateException() {
    }

    public ContextCreateException(String message) {
        super(message);
    }

    public ContextCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContextCreateException(Throwable cause) {
        super(cause);
    }

    public ContextCreateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
