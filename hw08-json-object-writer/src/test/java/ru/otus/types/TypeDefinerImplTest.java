package ru.otus.types;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class TypeDefinerImplTest {

    @Test
    @DisplayName("Должен правильно определить типы объектов")
    public void shouldDefineTypesCorrect() {
        FactoryDefinerType factory = new DefaultFactoryDefinerType();
        TypeDefiner typeDefiner = factory.createDefinerType();

        assertThat(typeDefiner.defineType(1)).isEqualTo(WritableType.NUMBER);
        assertThat(typeDefiner.defineType("s")).isEqualTo(WritableType.STRING);
        assertThat(typeDefiner.defineType('c')).isEqualTo(WritableType.CHAR);
        assertThat(typeDefiner.defineType(null)).isEqualTo(WritableType.NULL);
        assertThat(typeDefiner.defineType(new ArrayList<>())).isEqualTo(WritableType.ITERABLE);
        assertThat(typeDefiner.defineType(new Object[0])).isEqualTo(WritableType.ARRAY);
        assertThat(typeDefiner.defineType(true)).isEqualTo(WritableType.BOOLEAN);
        assertThat(typeDefiner.defineType(new Object())).isEqualTo(WritableType.CUSTOM_OBJECT);
    }

    @Test
    @DisplayName("Должен определить неизвестный тип объекта как UNKNOWN")
    public void shouldDefineUnknownTypeObjectAsUNKNOWN() {
        FactoryDefinerType factory = new DefaultFactoryDefinerType(Collections.emptyList());
        TypeDefiner typeDefiner = factory.createDefinerType();
        assertThat(typeDefiner.defineType(1)).isEqualTo(WritableType.UNKNOWN);
    }

}