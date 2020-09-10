package ru.otus.types.checkers;

import ru.otus.WritableType;

public class IterableWritableTypeChecker extends DefaultWritableTypeChecker {

    @Override
    public WritableType getType() {
        return WritableType.ITERABLE;
    }

    @Override
    protected Class<?> getAssignableClass() {
        return Iterable.class;
    }


}
