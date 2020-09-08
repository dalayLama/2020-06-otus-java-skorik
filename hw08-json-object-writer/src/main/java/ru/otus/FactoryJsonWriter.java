package ru.otus;

public interface FactoryJsonWriter {

    JsonWriter createJsonWriter(WritableType writableType);

}
