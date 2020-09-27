package ru.otus;

import ru.otus.exceptions.NotFoundWriterException;
import ru.otus.types.Type;

import java.util.HashMap;
import java.util.Map;

public class JsonWriterFactoryImpl implements JsonWriterFactory {

    private final Map<Type, JsonWriter> writers = new HashMap<>();

    public JsonWriterFactoryImpl() { }

    public void addWriters(Map<Type, JsonWriter> writerMap) {
        writers.putAll(writerMap);
    }

    @Override
    public JsonWriter createJsonWriter(Type type) {
        JsonWriter writer = writers.getOrDefault(type, null);
        if (writer == null) {
            throw new NotFoundWriterException("Didn't find writer for type \"" + type.name() + "\"");
        }
        return writer;
    }



}
