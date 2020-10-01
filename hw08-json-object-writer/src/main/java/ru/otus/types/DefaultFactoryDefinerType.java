package ru.otus.types;

import ru.otus.types.checkers.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultFactoryDefinerType implements FactoryDefinerType {

    private final Set<WritableTypeChecker> checkers = new HashSet<>();

    private TypeDefiner typeDefiner;

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
    public TypeDefiner createDefinerType() {
        if (typeDefiner == null) {
            typeDefiner = new TypeDefinerImpl(checkers);
        }
        return typeDefiner;
    }
}
