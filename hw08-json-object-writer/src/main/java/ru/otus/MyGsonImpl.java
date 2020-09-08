package ru.otus;

public class MyGsonImpl implements MyGson {

    private final DefinerType definerType;

    private final FactoryJsonWriter factoryWriter;

    public MyGsonImpl(DefinerType definerType, FactoryJsonWriter factoryWriter) {
        this.definerType = definerType;
        this.factoryWriter = factoryWriter;
    }

    @Override
    public String toJson(Object object) {
        WritableType writableType = definerType.defineType(object);
        JsonWriter jsonWriter = factoryWriter.createJsonWriter(writableType);
        return jsonWriter.toJson(object);
    }
}
