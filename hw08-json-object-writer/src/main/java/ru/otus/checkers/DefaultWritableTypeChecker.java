package ru.otus.checkers;

import ru.otus.WritableTypeChecker;

import java.util.Objects;

public abstract class DefaultWritableTypeChecker implements WritableTypeChecker {

    @Override
    public boolean isKnowType(Object object) {
        return Objects.nonNull(object) && object.getClass().isAssignableFrom(getAssignableClass());
    }

    protected abstract Class<?> getAssignableClass();

}
