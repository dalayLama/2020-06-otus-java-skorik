package ru.otus.jdbc.mapper.exceptions;

public class ReadObjectFieldsValues extends JdbcMapperException {

    private final Object objectData;

    public  ReadObjectFieldsValues(Object objectData) {
        this.objectData = objectData;
    }

    public ReadObjectFieldsValues(String message, Object objectData) {
        super(message);
        this.objectData = objectData;
    }

    public ReadObjectFieldsValues(String message, Throwable cause, Object objectData) {
        super(message, cause);
        this.objectData = objectData;
    }

    public ReadObjectFieldsValues(Throwable cause, Object objectData) {
        super(cause);
        this.objectData = objectData;
    }

    public ReadObjectFieldsValues(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object objectData) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.objectData = objectData;
    }

}
