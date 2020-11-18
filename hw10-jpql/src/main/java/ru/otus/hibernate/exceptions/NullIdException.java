package ru.otus.hibernate.exceptions;

public class NullIdException extends HibernateDaoException {

    public NullIdException() {
    }

    public NullIdException(String message) {
        super(message);
    }

    public NullIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullIdException(Throwable cause) {
        super(cause);
    }

    public NullIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
