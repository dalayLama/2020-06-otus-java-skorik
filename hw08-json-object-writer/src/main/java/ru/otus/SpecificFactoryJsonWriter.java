package ru.otus;

import ru.otus.types.Type;

public interface SpecificFactoryJsonWriter {

    JsonWriter createJsonWriter();

    Type getSupportedWritableType();

}
