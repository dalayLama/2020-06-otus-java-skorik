package ru.otus.types.checkers;

import ru.otus.types.Type;

public class StringWritableTypeChecker extends DefaultWritableTypeChecker {

    @Override
    public Type getType() {
        return Type.STRING;
    }

    @Override
    protected Class<?> getAssignableClass() {
        return String.class;
    }
}
