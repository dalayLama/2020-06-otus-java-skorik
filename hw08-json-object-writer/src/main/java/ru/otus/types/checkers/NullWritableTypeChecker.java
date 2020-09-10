package ru.otus.types.checkers;

import ru.otus.WritableType;
import ru.otus.WritableTypeChecker;

import java.util.Objects;

public class NullWritableTypeChecker implements WritableTypeChecker {

    @Override
    public boolean isKnowType(Object object) {
        return Objects.isNull(object);
    }

    @Override
    public WritableType getType() {
        return WritableType.NULL;
    }
}
