package ru.otus.hibernate.exceptions;

import ru.otus.hibernate.exceptions.HibernateDaoException;

public class NotNullIdException extends HibernateDaoException {

    public NotNullIdException() {
    }

    public NotNullIdException(String message) {
        super(message);
    }

    public NotNullIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotNullIdException(Throwable cause) {
        super(cause);
    }

    public NotNullIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
