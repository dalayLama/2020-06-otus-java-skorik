package ru.otus;

public class MyGsonImpl implements MyGson {

    private final WriterDefiner writerDefiner;

    public MyGsonImpl(WriterDefiner writerDefiner) {
        if (writerDefiner == null) {
            throw new NullPointerException("writerDefiner can't be null");
        }
        this.writerDefiner = writerDefiner;
    }

    @Override
    public String toJson(Object object) {
        JsonWriter writer = writerDefiner.getWriter(object);
        return writer.toJson(object);
    }
}
