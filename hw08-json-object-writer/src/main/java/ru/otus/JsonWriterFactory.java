package ru.otus;

import ru.otus.types.Type;

public interface JsonWriterFactory {

    JsonWriter createJsonWriter(Type type);

}
