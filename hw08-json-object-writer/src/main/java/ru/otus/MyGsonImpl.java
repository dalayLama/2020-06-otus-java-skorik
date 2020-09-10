package ru.otus;

import ru.otus.types.TypeDefiner;
import ru.otus.types.WritableType;

public class MyGsonImpl implements MyGson {

    private final TypeDefiner typeDefiner;

    private final FactoryJsonWriter factoryWriter;

    public MyGsonImpl(TypeDefiner typeDefiner, FactoryJsonWriter factoryWriter) {
        this.typeDefiner = typeDefiner;
        this.factoryWriter = factoryWriter;
    }

    @Override
    public String toJson(Object object) {
        WritableType writableType = typeDefiner.defineType(object);
        JsonWriter jsonWriter = factoryWriter.createJsonWriter(writableType);
        return jsonWriter.toJson(object);
    }
}
