package ru.otus.types.checkers;

import ru.otus.types.Type;

public class BooleanWritableTypeChecker extends DefaultWritableTypeChecker {

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }

    @Override
    protected Class<?> getAssignableClass() {
        return Boolean.class;
    }
}
