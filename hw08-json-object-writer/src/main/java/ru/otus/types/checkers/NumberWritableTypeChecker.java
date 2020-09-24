package ru.otus.types.checkers;

import ru.otus.types.Type;

public class NumberWritableTypeChecker extends DefaultWritableTypeChecker {

    @Override
    public Type getType() {
        return Type.NUMBER;
    }

    @Override
    protected Class<?> getAssignableClass() {
        return Number.class;
    }
}
