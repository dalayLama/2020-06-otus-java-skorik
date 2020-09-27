package ru.otus.factories;

import ru.otus.JsonWriter;
import ru.otus.types.Type;

public interface JsonWriterFactory {

    JsonWriter createJsonWriter(Type type);

}
