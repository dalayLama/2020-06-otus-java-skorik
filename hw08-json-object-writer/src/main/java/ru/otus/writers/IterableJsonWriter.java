package ru.otus.writers;

import ru.otus.WriterDefiner;

import java.util.ArrayList;
import java.util.List;

public class IterableJsonWriter extends CollectionJsonWriter {

    public IterableJsonWriter(WriterDefiner writerDefiner) {
        super(writerDefiner);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected List toList(Object object) {
        if (List.class.isAssignableFrom(object.getClass())) {
            return (List) object;
        }
        Iterable iterable = (Iterable) object;
        List list = new ArrayList();
        for (Object item : iterable) {
            list.add(item);
        }
        return list;
    }
}
