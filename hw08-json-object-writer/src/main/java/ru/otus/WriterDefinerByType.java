package ru.otus;

import ru.otus.factories.JsonWriterFactory;
import ru.otus.types.TypeDefiner;
import ru.otus.types.Type;

public class WriterDefinerByType implements WriterDefiner {

    private final TypeDefiner typeDefiner;

    private final JsonWriterFactory jsonWriterFactory;

    public WriterDefinerByType(TypeDefiner typeDefiner, JsonWriterFactory jsonWriterFactory) {
        this.typeDefiner = typeDefiner;
        this.jsonWriterFactory = jsonWriterFactory;
    }

    @Override
    public JsonWriter getWriter(Object object) {
        Type type = typeDefiner.defineType(object);
        return jsonWriterFactory.createJsonWriter(type);
    }
}
