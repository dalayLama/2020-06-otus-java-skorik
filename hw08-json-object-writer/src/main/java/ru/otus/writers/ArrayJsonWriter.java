package ru.otus.writers;

import ru.otus.WriterDefiner;
import ru.otus.exceptions.DefiningTypeException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayJsonWriter extends CollectionJsonWriter {

    public ArrayJsonWriter(WriterDefiner writerDefiner) {
        super(writerDefiner);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected List toList(Object object) {
        Class<?> componentType = object.getClass().getComponentType();
        if (componentType.isPrimitive()) {
            return definePrimitiveArray(componentType, object);
        }
        return List.of((Object[]) object);
    }

    @SuppressWarnings("rawtypes")
    private List definePrimitiveArray(Class<?> type, Object object) {
        if (type.equals(Byte.TYPE)) {
            //todo
            return List.of((byte[]) object);
        } if (type.equals(Short.TYPE)) {
            //todo
            return List.of((short[]) object);
        } if (type.equals(Integer.TYPE)) {
            return Arrays.stream((int[]) object).boxed().collect(Collectors.toList());
        } if (type.equals(Long.TYPE)) {
            return List.of((long[]) object);
        } if (type.equals(Float.TYPE)) {
            return List.of((float[]) object);
        } if (type.equals(Double.TYPE)) {
            return List.of((double[]) object);
        } if (type.equals(Boolean.TYPE)) {
            return List.of((boolean[]) object);
        } if (type.equals(Character.TYPE)) {
            return List.of((char[]) object);
        } else {
            throw new DefiningTypeException("failed to define type array, componentType - "
                    + type.getName() + ", array - " + object);
        }
    }

}
