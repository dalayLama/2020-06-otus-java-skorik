package ru.otus.types.checkers;

import ru.otus.types.WritableType;

public class NumberWritableTypeChecker extends DefaultWritableTypeChecker {

    @Override
    public WritableType getType() {
        return WritableType.NUMBER;
    }

    @Override
    protected Class<?> getAssignableClass() {
        return Number.class;
    }
}
