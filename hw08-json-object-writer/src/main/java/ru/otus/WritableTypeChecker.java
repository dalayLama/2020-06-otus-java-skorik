package ru.otus;

public interface WritableTypeChecker {

    boolean isKnowType(Object object);

    WritableType getType();

}
