package ru.otus.writers;

import ru.otus.JsonWriter;
import ru.otus.WriterDefiner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomObjectJsonWriter implements JsonWriter {

    private static final String START_ELEMENT = "{";

    private static final String END_ELEMENT = "}";

    private final WriterDefiner writerDefiner;

    private final ReaderFields readerFields;

    public CustomObjectJsonWriter(WriterDefiner writerDefiner, ReaderFields readerFields) {
        this.writerDefiner = writerDefiner;
        this.readerFields = readerFields;
    }

    @Override
    public String toJson(Object object) {
        Collection<? extends FieldInfo> fields = readerFields.read(object);
        List<String> elements = new ArrayList<>(fields.size());
        for (FieldInfo fi : fields) {
            JsonWriter writer = writerDefiner.getWriter(fi.getValue());
            String elementValue = writer.toJson(fi.getValue());
            elements.add(createElement(fi.getFieldName(), elementValue));
        }
        return wrapElements(elements);
    }

    private String createElement(String fieldName, String value) {
        return String.format("%s:%s", fieldName, value);
    }

    private String wrapElements(List<String> elements) {
        String join = String.join(",", elements);
        return START_ELEMENT + join + END_ELEMENT;
    }

}
