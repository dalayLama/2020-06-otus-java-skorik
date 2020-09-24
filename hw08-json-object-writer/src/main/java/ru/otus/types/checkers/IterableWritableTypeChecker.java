package ru.otus.types.checkers;

import ru.otus.types.Type;

public class IterableWritableTypeChecker extends DefaultWritableTypeChecker {

    @Override
    public Type getType() {
        return Type.ITERABLE;
    }

    @Override
    protected Class<?> getAssignableClass() {
        return Iterable.class;
    }


}
