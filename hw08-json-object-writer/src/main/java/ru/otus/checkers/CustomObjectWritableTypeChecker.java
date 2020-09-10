package ru.otus.checkers;

import ru.otus.WritableType;
import ru.otus.WritableTypeChecker;

import java.util.Objects;

public class CustomObjectWritableTypeChecker implements WritableTypeChecker {

    @Override
    public boolean isKnowType(Object object) {
        return Objects.nonNull(object) &&
                !Number.class.isAssignableFrom(object.getClass()) &&
                !String.class.isAssignableFrom(object.getClass()) &&
                !Character.class.isAssignableFrom(object.getClass()) &&
                !Boolean.class.isAssignableFrom(object.getClass()) &&
                !Iterable.class.isAssignableFrom(object.getClass()) &&
                !object.getClass().isArray() &&
                !object.getClass().isPrimitive();
    }

    @Override
    public WritableType getType() {
        return WritableType.CUSTOM_OBJECT;
    }
}
