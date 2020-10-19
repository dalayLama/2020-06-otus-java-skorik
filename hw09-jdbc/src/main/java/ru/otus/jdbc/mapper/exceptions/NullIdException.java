package ru.otus.jdbc.mapper.exceptions;

public class NullIdException extends JdbcMapperException {

    private final Object objectData;

    public NullIdException(Object objectData) {
        this.objectData = objectData;
    }

    public NullIdException(String message, Object objectData) {
        super(message);
        this.objectData = objectData;
    }

    public NullIdException(String message, Throwable cause, Object objectData) {
        super(message, cause);
        this.objectData = objectData;
    }

    public NullIdException(Throwable cause, Object objectData) {
        super(cause);
        this.objectData = objectData;
    }

    public NullIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object objectData) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.objectData = objectData;
    }

}
