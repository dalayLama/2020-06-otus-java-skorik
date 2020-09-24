package ru.otus.exceptions;

public class DefiningTypeException extends MyGsonException {

    public DefiningTypeException() {
    }

    public DefiningTypeException(String message) {
        super(message);
    }

    public DefiningTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefiningTypeException(Throwable cause) {
        super(cause);
    }

    public DefiningTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
