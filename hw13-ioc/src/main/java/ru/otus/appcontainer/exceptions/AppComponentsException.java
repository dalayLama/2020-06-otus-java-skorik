package ru.otus.appcontainer.exceptions;

public class AppComponentsException extends RuntimeException {

    public AppComponentsException() {
    }

    public AppComponentsException(String message) {
        super(message);
    }

    public AppComponentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppComponentsException(Throwable cause) {
        super(cause);
    }

    public AppComponentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
