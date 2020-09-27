package ru.otus.writers;

import ru.otus.exceptions.ReadFieldsException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReflectionReaderFields implements ReaderFields {

    @Override
    public Collection<? extends FieldInfo> read(Object object) {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        List<FieldInfo> result = new ArrayList<>(declaredFields.length);
        try {
            for (Field f : declaredFields) {
                if (f.isSynthetic()) {
                    continue;
                }
                f.setAccessible(true);
                result.add(new FieldInfo(f.getName(), f.get(object)));
            }
        } catch (Exception e) {
            throw new ReadFieldsException(e.getMessage(), e);
        }
        return result;
    }
}
