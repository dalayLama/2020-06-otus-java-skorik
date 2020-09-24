package ru.otus.types.checkers;

import ru.otus.types.Type;
import ru.otus.types.WritableTypeChecker;

import java.util.Objects;

public class NullWritableTypeChecker implements WritableTypeChecker {

    @Override
    public boolean isKnowType(Object object) {
        return Objects.isNull(object);
    }

    @Override
    public Type getType() {
        return Type.NULL;
    }
}
