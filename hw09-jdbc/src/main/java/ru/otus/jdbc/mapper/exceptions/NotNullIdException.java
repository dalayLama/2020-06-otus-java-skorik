package ru.otus.jdbc.mapper.exceptions;

public class NotNullIdException extends JdbcMapperException {

    private final Object objectData;

    public NotNullIdException(Object objectData) {
        this.objectData = objectData;
    }

    public NotNullIdException(String message, Object objectData) {
        super(message);
        this.objectData = objectData;
    }

    public NotNullIdException(String message, Throwable cause, Object objectData) {
        super(message, cause);
        this.objectData = objectData;
    }

    public NotNullIdException(Throwable cause, Object objectData) {
        super(cause);
        this.objectData = objectData;
    }

    public NotNullIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object objectData) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.objectData = objectData;
    }

}
