package ru.otus.writers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReflectionReaderFieldsTest {

    @Test
    @DisplayName("Должен прочитать все поля класса")
    public void shouldReadAllFieldsClass() {
        List<FieldInfo> expectResult = List.of(
                new FieldInfo("testField1", 2),
                new FieldInfo("testField2", "str")
        );
        ReflectionReaderFields reader = new ReflectionReaderFields();
        List<FieldInfo> results = new ArrayList<>(reader.read(new TestClass()));
        assertThat(expectResult).containsAll(results);
    }

    private static class TestClass {

        private int testField1 = 2;

        private String testField2 = "str";

    }

}