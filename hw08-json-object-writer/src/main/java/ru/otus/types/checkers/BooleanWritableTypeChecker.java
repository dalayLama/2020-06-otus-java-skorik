package ru.otus.types.checkers;

import ru.otus.WritableType;

public class BooleanWritableTypeChecker extends DefaultWritableTypeChecker {

    @Override
    public WritableType getType() {
        return WritableType.BOOLEAN;
    }

    @Override
    protected Class<?> getAssignableClass() {
        return Boolean.class;
    }
}
