package ru.otus.types.checkers;

import ru.otus.types.WritableType;

public class CharWritableTypeChecker extends DefaultWritableTypeChecker {

    @Override
    public WritableType getType() {
        return WritableType.CHAR;
    }

    @Override
    protected Class<?> getAssignableClass() {
        return Character.class;
    }
}
