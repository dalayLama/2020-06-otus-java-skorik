package ru.otus.writers;

import ru.otus.WriterDefiner;
import ru.otus.exceptions.DefiningTypeException;

import java.util.ArrayList;
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
            List<Byte> bytes = new ArrayList<>();
            for (byte b : (byte[]) object) {
                bytes.add(b);
            }
            return bytes;
        } if (type.equals(Short.TYPE)) {
            List<Short> shorts = new ArrayList<>();
            for (short s : (short[]) object) {
                shorts.add(s);
            }
            return shorts;
        } if (type.equals(Integer.TYPE)) {
            return Arrays.stream((int[]) object).boxed().collect(Collectors.toList());
        } if (type.equals(Long.TYPE)) {
            return Arrays.stream((long[]) object).boxed().collect(Collectors.toList());
        } if (type.equals(Float.TYPE)) {
            List<Float> floats = new ArrayList<>();
            for (float f : (float[]) object) {
                floats.add(f);
            }
            return floats;
        } if (type.equals(Double.TYPE)) {
            return Arrays.stream((double[]) object).boxed().collect(Collectors.toList());
        } if (type.equals(Boolean.TYPE)) {
            List<Boolean> booleans = new ArrayList<>();
            for (boolean b : (boolean[]) object) {
                booleans.add(b);
            }
            return booleans;
        } if (type.equals(Character.TYPE)) {
            List<Character> characters = new ArrayList<>();
            for (char c : (char[]) object) {
                characters.add(c);
            }
            return characters;
        } else {
            throw new DefiningTypeException("failed to define type array, componentType - "
                    + type.getName() + ", array - " + object);
        }
    }

}
