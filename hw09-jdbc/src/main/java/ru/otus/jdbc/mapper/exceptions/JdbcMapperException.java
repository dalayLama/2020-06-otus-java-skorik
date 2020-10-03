package ru.otus.jdbc.mapper.exceptions;

public class JdbcMapperException extends RuntimeException {

    public JdbcMapperException() {
    }

    public JdbcMapperException(String message) {
        super(message);
    }

    public JdbcMapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public JdbcMapperException(Throwable cause) {
        super(cause);
    }

    public JdbcMapperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
