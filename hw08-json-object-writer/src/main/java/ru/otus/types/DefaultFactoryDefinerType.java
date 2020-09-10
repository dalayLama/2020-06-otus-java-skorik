package ru.otus.types;

import ru.otus.WritableTypeChecker;
import ru.otus.types.checkers.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultFactoryDefinerType implements FactoryDefinerType {

    private final Set<WritableTypeChecker> checkers = new HashSet<>();

    private DefinerType definerType;

    public DefaultFactoryDefinerType(Collection<? extends WritableTypeChecker> checkers) {
        this.checkers.addAll(checkers);
    }

    public DefaultFactoryDefinerType() {
        this(List.of(
                new CustomObjectWritableTypeChecker(),
                new StringWritableTypeChecker(),
                new NumberWritableTypeChecker(),
                new IterableWritableTypeChecker(),
                new ArrayWritableTypeChecker(),
                new CharWritableTypeChecker(),
                new BooleanWritableTypeChecker(),
                new NullWritableTypeChecker()
        ));
    }

    @Override
    public DefinerType createDefinerType() {
        if (definerType == null) {
            definerType = new DefinerTypeImpl(checkers);
        }
        return definerType;
    }
}
