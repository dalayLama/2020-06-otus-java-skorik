package ru.otus.types;

public interface WritableTypeChecker {

    boolean isKnowType(Object object);

    WritableType getType();

}
