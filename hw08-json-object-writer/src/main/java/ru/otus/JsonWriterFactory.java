package ru.otus;

import ru.otus.types.WritableType;

public interface JsonWriterFactory {

    JsonWriter createJsonWriter(WritableType writableType);

}
