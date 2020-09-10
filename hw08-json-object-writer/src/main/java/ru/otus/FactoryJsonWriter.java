package ru.otus;

import ru.otus.types.WritableType;

public interface FactoryJsonWriter {

    JsonWriter createJsonWriter(WritableType writableType);

}
