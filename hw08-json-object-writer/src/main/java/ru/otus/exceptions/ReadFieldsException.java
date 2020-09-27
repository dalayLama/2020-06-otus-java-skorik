package ru.otus.exceptions;

public class ReadFieldsException extends MyGsonException {

    public ReadFieldsException() {
    }

    public ReadFieldsException(String message) {
        super(message);
    }

    public ReadFieldsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadFieldsException(Throwable cause) {
        super(cause);
    }

    public ReadFieldsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
