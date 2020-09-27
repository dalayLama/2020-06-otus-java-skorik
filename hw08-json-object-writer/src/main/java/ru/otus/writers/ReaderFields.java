package ru.otus.writers;

import java.util.Collection;

public interface ReaderFields {

    Collection<? extends FieldInfo> read(Object object);

}
