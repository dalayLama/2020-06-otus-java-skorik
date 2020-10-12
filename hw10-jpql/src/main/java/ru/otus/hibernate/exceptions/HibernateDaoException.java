package ru.otus.hibernate.exceptions;

public class HibernateDaoException extends RuntimeException {

    public HibernateDaoException() {
    }

    public HibernateDaoException(String message) {
        super(message);
    }

    public HibernateDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public HibernateDaoException(Throwable cause) {
        super(cause);
    }

    public HibernateDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
