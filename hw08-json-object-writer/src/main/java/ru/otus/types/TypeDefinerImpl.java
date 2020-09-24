package ru.otus.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TypeDefinerImpl implements TypeDefiner {

    private final List<WritableTypeChecker> checkers;

    TypeDefinerImpl(Collection<? extends WritableTypeChecker> checkers) {
        this.checkers = new ArrayList<>(checkers);
    }

    @Override
    public Type defineType(Object object) {
        return checkers.stream()
                .filter(checker -> checker.isKnowType(object))
                .map(WritableTypeChecker::getType)
                .findFirst().orElse(Type.UNKNOWN);
    }

}
