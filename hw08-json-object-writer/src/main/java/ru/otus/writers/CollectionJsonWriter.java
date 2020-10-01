package ru.otus.writers;

import ru.otus.JsonWriter;
import ru.otus.WriterDefiner;

import java.util.ArrayList;
import java.util.List;

public abstract class CollectionJsonWriter implements JsonWriter {

    private final static String START_ELEMENT = "[";

    private final static String END_ELEMENT = "]";

    private final WriterDefiner writerDefiner;

    public CollectionJsonWriter(WriterDefiner writerDefiner) {
        this.writerDefiner = writerDefiner;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public String toJson(Object object) {
        List objects = toList(object);
        if (objects.size() == 0) {
            return emptyElement();
        }

        List<String> arrayElements = new ArrayList<>(objects.size());
        for (Object o : objects) {
            var writer = writerDefiner.getWriter(o);
            arrayElements.add(writer.toJson(o));
        }
        return wrapElements(arrayElements);
    }

    @SuppressWarnings("rawtypes")
    protected abstract List toList(Object object);

    private String emptyElement() {
        return START_ELEMENT + END_ELEMENT;
    }

    private String wrapElements(List<String> arrayElements) {
        String join = String.join(", ", arrayElements);
        return START_ELEMENT + join + END_ELEMENT;
    }



}
