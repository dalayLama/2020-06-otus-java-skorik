package ru.otus.exceptions;

public class NotFoundWriterException extends MyGsonException {

    public NotFoundWriterException() {
    }

    public NotFoundWriterException(String message) {
        super(message);
    }

    public NotFoundWriterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundWriterException(Throwable cause) {
        super(cause);
    }

    public NotFoundWriterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
