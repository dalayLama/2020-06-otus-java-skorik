package ru.otus.writers;

import ru.otus.JsonWriter;

public class SimpleJsonWriter implements JsonWriter {

    @Override
    public String toJson(Object object) {
        return String.valueOf(object);
    }
}
