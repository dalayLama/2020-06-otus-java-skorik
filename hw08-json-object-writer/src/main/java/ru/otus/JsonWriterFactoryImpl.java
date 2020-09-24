package ru.otus;

import ru.otus.exceptions.NotFoundWriterException;
import ru.otus.types.Type;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonWriterFactoryImpl implements JsonWriterFactory {

    private final Map<Type, SpecificFactoryJsonWriter> writersFactories = new HashMap<>();

    public JsonWriterFactoryImpl(Collection<? extends SpecificFactoryJsonWriter> writersFactories) {
        addFactoryWriters(writersFactories);
    }

    public void addFactoryWriters(SpecificFactoryJsonWriter... writersFactories) {
        addFactoryWriters(List.of(writersFactories));
    }

    public void addFactoryWriters(Collection<? extends SpecificFactoryJsonWriter> writersFactories) {
        writersFactories.forEach(f -> this.writersFactories.put(f.getSupportedWritableType(), f));
    }

    @Override
    public JsonWriter createJsonWriter(Type type) {
        SpecificFactoryJsonWriter jw = writersFactories.getOrDefault(type, null);
        if (jw == null) {
            throw new NotFoundWriterException("Didn't find writer for type \"" + type.name() + "\"");
        }
        return jw.createJsonWriter();
    }



}
