package ru.otus.writers;

import java.util.Objects;

public class FieldInfo {

    private final String fieldName;

    private final Object value;

    public FieldInfo(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldInfo fieldInfo = (FieldInfo) o;
        return Objects.equals(getFieldName(), fieldInfo.getFieldName()) &&
                Objects.equals(getValue(), fieldInfo.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFieldName(), getValue());
    }
}
