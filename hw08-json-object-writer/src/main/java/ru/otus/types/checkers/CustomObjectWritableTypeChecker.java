package ru.otus.types.checkers;

import ru.otus.types.Type;
import ru.otus.types.WritableTypeChecker;

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
    public Type getType() {
        return Type.CUSTOM_OBJECT;
    }
}
