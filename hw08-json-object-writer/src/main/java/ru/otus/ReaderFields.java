package ru.otus;

import ru.otus.writers.FieldInfo;

import java.util.Collection;

public interface ReaderFields {

    Collection<? extends FieldInfo> read(Object object);

}
