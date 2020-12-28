package ru.otus.appcontainer.exceptions;

public class CreatingComponentException extends AppComponentsException {

    public CreatingComponentException() {
    }

    public CreatingComponentException(String message) {
        super(message);
    }

    public CreatingComponentException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreatingComponentException(Throwable cause) {
        super(cause);
    }

    public CreatingComponentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
