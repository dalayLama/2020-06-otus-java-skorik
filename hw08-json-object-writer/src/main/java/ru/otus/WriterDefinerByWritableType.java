package ru.otus;

import ru.otus.types.TypeDefiner;
import ru.otus.types.WritableType;

public class WriterDefinerByWritableType implements WriterDefiner {

    private final TypeDefiner typeDefiner;

    private final JsonWriterFactory jsonWriterFactory;

    public WriterDefinerByWritableType(TypeDefiner typeDefiner, JsonWriterFactory jsonWriterFactory) {
        this.typeDefiner = typeDefiner;
        this.jsonWriterFactory = jsonWriterFactory;
    }

    @Override
    public JsonWriter getWriter(Object object) {
        WritableType writableType = typeDefiner.defineType(object);
        return jsonWriterFactory.createJsonWriter(writableType);
    }
}
