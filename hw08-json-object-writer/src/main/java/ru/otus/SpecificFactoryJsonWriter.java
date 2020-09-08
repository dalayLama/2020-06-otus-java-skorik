package ru.otus;

public interface SpecificFactoryJsonWriter {

    JsonWriter createJsonWriter();

    WritableType getSupportedWritableType();

}
