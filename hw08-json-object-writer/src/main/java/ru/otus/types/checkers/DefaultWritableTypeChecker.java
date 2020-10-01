package ru.otus.types.checkers;

import ru.otus.types.WritableTypeChecker;

import java.util.Objects;

public abstract class DefaultWritableTypeChecker implements WritableTypeChecker {

    @Override
    public boolean isKnowType(Object object) {
        return Objects.nonNull(object) && getAssignableClass().isAssignableFrom(object.getClass());
    }

    protected abstract Class<?> getAssignableClass();

}
