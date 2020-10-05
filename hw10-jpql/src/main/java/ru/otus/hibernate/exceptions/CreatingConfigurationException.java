package ru.otus.hibernate.exceptions;

public class CreatingConfigurationException extends RuntimeException {
    public CreatingConfigurationException() {
    }

    public CreatingConfigurationException(String message) {
        super(message);
    }

    public CreatingConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreatingConfigurationException(Throwable cause) {
        super(cause);
    }

    public CreatingConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
