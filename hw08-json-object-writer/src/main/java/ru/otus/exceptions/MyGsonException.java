package ru.otus.exceptions;

public class MyGsonException extends RuntimeException {

    public MyGsonException() {
    }

    public MyGsonException(String message) {
        super(message);
    }

    public MyGsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyGsonException(Throwable cause) {
        super(cause);
    }

    public MyGsonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
