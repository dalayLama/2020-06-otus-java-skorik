package ru.otus.jdbc.mapper.impls;

import ru.otus.jdbc.mapper.IdNullValueChecker;

import java.lang.reflect.Field;
import java.util.Objects;

@SuppressWarnings("rawtypes")
public class DefaultIdNullValueChecker implements IdNullValueChecker {

    public static long DEFAULT_NULL_VALUE = 0L;

    private final Field idField;

    private final Object nullValue;

    public DefaultIdNullValueChecker(Field idField, Object nullValue) {
        this.idField = idField;
        this.nullValue = nullValue;
    }

    public DefaultIdNullValueChecker(Field idField) {
        this(idField, DEFAULT_NULL_VALUE);
    }

    @Override
    public boolean check(Object objectData) {
        try {
            Object idValue = idField.get(objectData);
            return Objects.equals(nullValue, idValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
