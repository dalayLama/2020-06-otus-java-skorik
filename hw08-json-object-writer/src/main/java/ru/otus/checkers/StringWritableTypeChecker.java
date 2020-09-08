package ru.otus.checkers;

import ru.otus.WritableType;

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
