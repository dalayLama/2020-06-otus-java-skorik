package ru.otus.writers;

import ru.otus.JsonWriter;

public class SimpleJsonWriter implements JsonWriter {

    private final String wrapStr;

    public SimpleJsonWriter(String wrapStr) {
        this.wrapStr = wrapStr;
    }

    public SimpleJsonWriter() {
        this(null);
    }

    @Override
    public String toJson(Object object) {
        if (wrapStr == null || wrapStr.isEmpty()) {
            return String.valueOf(object);
        } else {
            return object != null ? String.format("%s%s%s", wrapStr, object, wrapStr) : "null";
        }

    }
}
