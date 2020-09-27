package ru.otus.factories;

import ru.otus.MyGson;
import ru.otus.MyGsonImpl;
import ru.otus.WriterDefiner;
import ru.otus.WriterDefinerByType;
import ru.otus.types.DefaultFactoryDefinerType;
import ru.otus.types.TypeDefiner;
import ru.otus.ReflectionReaderFields;
import ru.otus.writers.kits.DefaultKit;

public class FactoryMyGson {

    public MyGson createMyGson() {
        return new MyGsonImpl(createDefiner());
    }

    private WriterDefiner createDefiner() {
        TypeDefiner definerType = new DefaultFactoryDefinerType().createDefinerType();
        JsonWriterFactoryImpl writerFactory = new JsonWriterFactoryImpl();
        WriterDefinerByType definer = new WriterDefinerByType(definerType, writerFactory);
        loadWriter(writerFactory, definer);
        return definer;
    }

    private void loadWriter(JsonWriterFactoryImpl writerFactory, WriterDefiner definer) {
        ReflectionReaderFields reader = new ReflectionReaderFields();
        DefaultKit kit = new DefaultKit(definer, reader);
        writerFactory.addWriters(kit.getMatches());
    }

}
