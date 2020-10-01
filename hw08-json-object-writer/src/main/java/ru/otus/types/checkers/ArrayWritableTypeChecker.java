package ru.otus.types.checkers;

import ru.otus.types.Type;
import ru.otus.types.WritableTypeChecker;

import java.util.Objects;

public class ArrayWritableTypeChecker implements WritableTypeChecker {

    @Override
    public boolean isKnowType(Object object) {
        return Objects.nonNull(object) && object.getClass().isArray();
    }

    @Override
    public Type getType() {
        return Type.ARRAY;
    }
}
