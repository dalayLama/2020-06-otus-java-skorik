package ru.otus;

import ru.otus.types.WritableType;

public interface SpecificFactoryJsonWriter {

    JsonWriter createJsonWriter();

    WritableType getSupportedWritableType();

}
