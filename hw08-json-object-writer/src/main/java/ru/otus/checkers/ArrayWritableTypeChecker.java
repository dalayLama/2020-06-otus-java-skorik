package ru.otus.checkers;

import ru.otus.WritableType;
import ru.otus.WritableTypeChecker;

import java.util.Objects;

public class ArrayWritableTypeChecker implements WritableTypeChecker {

    @Override
    public boolean isKnowType(Object object) {
        return Objects.nonNull(object) && object.getClass().isArray();
    }

    @Override
    public WritableType getType() {
        return WritableType.ARRAY;
    }
}
