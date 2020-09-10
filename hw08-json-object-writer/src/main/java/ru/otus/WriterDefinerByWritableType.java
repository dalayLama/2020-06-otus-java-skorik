package ru.otus;

import ru.otus.types.TypeDefiner;
import ru.otus.types.WritableType;

public class WriterDefinerByWritableType implements WriterDefiner {

    private final TypeDefiner typeDefiner;

    private final FactoryJsonWriter factoryJsonWriter;

    public WriterDefinerByWritableType(TypeDefiner typeDefiner, FactoryJsonWriter factoryJsonWriter) {
        this.typeDefiner = typeDefiner;
        this.factoryJsonWriter = factoryJsonWriter;
    }

    @Override
    public JsonWriter getWriter(Object object) {
        WritableType writableType = typeDefiner.defineType(object);
        return factoryJsonWriter.createJsonWriter(writableType);
    }
}
