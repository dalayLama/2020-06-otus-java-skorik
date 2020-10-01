package ru.otus;

import ru.otus.exceptions.ReadFieldsException;
import ru.otus.writers.FieldInfo;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ReflectionReaderFields implements ReaderFields {

    @Override
    public Collection<? extends FieldInfo> read(Object object) {
        List<Field> fields = getFields(object.getClass());
        List<FieldInfo> result = new ArrayList<>(fields.size());
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                result.add(new FieldInfo(f.getName(), f.get(object)));
            }
        } catch (Exception e) {
            throw new ReadFieldsException(e.getMessage(), e);
        }
        return result;
    }

    private List<Field> getFields(Class<?> clazz) {
        if (clazz == null) {
            return Collections.emptyList();
        }

        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> !f.isSynthetic())
                .collect(Collectors.toList());
        fields.addAll(getFields(clazz.getSuperclass()));
        return fields;
    }

}
