package ru.otus.types.checkers;

import ru.otus.types.Type;

public class CharWritableTypeChecker extends DefaultWritableTypeChecker {

    @Override
    public Type getType() {
        return Type.CHAR;
    }

    @Override
    protected Class<?> getAssignableClass() {
        return Character.class;
    }
}
