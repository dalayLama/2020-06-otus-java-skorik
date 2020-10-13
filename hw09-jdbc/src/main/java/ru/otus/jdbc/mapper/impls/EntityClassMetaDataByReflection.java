package ru.otus.jdbc.mapper.impls;

import ru.otus.core.annotations.Id;
import ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.jdbc.mapper.exceptions.ReadEntityException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class EntityClassMetaDataByReflection<T> implements EntityClassMetaData<T> {

    private final String name;

    private final Constructor<T> constructor;

    private final Field idField;

    private final List<Field> allFields = new ArrayList<>();

    public EntityClassMetaDataByReflection(Class<T> tClass) throws ReadEntityException {
        try {
            this.name = tClass.getSimpleName();
            this.constructor = tClass.getConstructor();
            ReadFieldsResult readFieldsResult = readFields(tClass);
            this.idField = readFieldsResult.getIdField();
            this.allFields.addAll(readFieldsResult.getFields());
        } catch (NoSuchMethodException e) {
            throw new ReadEntityException("default constructor not found", e);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Constructor<T> getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    @Override
    public List<Field> getAllFields() {
        return allFields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return allFields.stream()
                .filter(f -> f != idField)
                .collect(Collectors.toList());
    }

    private ReadFieldsResult readFields(Class<T> tClass) throws ReadEntityException {
        ReadFieldsResult readResult = _readFields(tClass);
        if (readResult.getIdField() == null) {
            throw new ReadEntityException("id field not found");
        }
        return readResult;
    }

    private ReadFieldsResult _readFields(Class<?> tClass) throws ReadEntityException {
        if (tClass == null) {
            return null;
        }
        ReadFieldsResult rr = new ReadFieldsResult();
        for (Field f : tClass.getDeclaredFields()) {
            if (f.isSynthetic()) {
                continue;
            }
            if (f.isAnnotationPresent(Id.class)) {
                rr.setIdField(f);
            }
            f.setAccessible(true);
            rr.addField(f);
        }
        rr.addValues(_readFields(tClass.getSuperclass()));
        return rr;
    }

    private static class ReadFieldsResult {

        private Field idField;

        private final Set<Field> allFields = new LinkedHashSet<>();

        public void addValues(ReadFieldsResult result) {
            if (result == null) {
                return;
            }
            if (result.getIdField() != null) {
                setIdField(result.getIdField());
            }
            allFields.addAll(result.getFields());
        }

        public Field getIdField() {
            return idField;
        }

        public void setIdField(Field idField) {
            if (this.idField != null) {
                throw new ReadEntityException("duplicate id field");
            }
            this.idField = idField;
        }

        public void addField(Field field) {
            this.allFields.add(field);
        }

        public Collection<Field> getFields() {
            return allFields;
        }

    }

}
