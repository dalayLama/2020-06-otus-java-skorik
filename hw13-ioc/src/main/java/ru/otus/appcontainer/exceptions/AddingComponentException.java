package ru.otus.appcontainer.exceptions;

public class AddingComponentException extends AppComponentsException {

    public AddingComponentException() {
    }

    public AddingComponentException(String message) {
        super(message);
    }

    public AddingComponentException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddingComponentException(Throwable cause) {
        super(cause);
    }

    public AddingComponentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
