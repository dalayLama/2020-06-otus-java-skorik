package ru.otus.appcontainer.exceptions;

public class NotFoundAppComponent extends RuntimeException {

    public NotFoundAppComponent() {
    }

    public NotFoundAppComponent(String message) {
        super(message);
    }

    public NotFoundAppComponent(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundAppComponent(Throwable cause) {
        super(cause);
    }

    public NotFoundAppComponent(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
