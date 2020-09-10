package ru.otus.types.checkers;

import ru.otus.types.WritableType;

public class StringWritableTypeChecker extends DefaultWritableTypeChecker {

    @Override
    public WritableType getType() {
        return WritableType.STRING;
    }

    @Override
    protected Class<?> getAssignableClass() {
        return String.class;
    }
}
