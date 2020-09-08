package ru.otus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefinerTypeImpl implements DefinerType {

    private final List<WritableTypeChecker> checkers;

    public DefinerTypeImpl(Collection<? extends WritableTypeChecker> checkers) {
        this.checkers = new ArrayList<>(checkers);
    }

    @Override
    public WritableType defineType(Object object) {
        return checkers.stream()
                .filter(checker -> checker.isKnowType(object))
                .map(WritableTypeChecker::getType)
                .findFirst().orElse(WritableType.UNKNOWN);
    }

}
