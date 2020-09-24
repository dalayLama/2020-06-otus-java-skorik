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

        assertThat(typeDefiner.defineType(1)).isEqualTo(Type.NUMBER);
        assertThat(typeDefiner.defineType("s")).isEqualTo(Type.STRING);
        assertThat(typeDefiner.defineType('c')).isEqualTo(Type.CHAR);
        assertThat(typeDefiner.defineType(null)).isEqualTo(Type.NULL);
        assertThat(typeDefiner.defineType(new ArrayList<>())).isEqualTo(Type.ITERABLE);
        assertThat(typeDefiner.defineType(new Object[0])).isEqualTo(Type.ARRAY);
        assertThat(typeDefiner.defineType(true)).isEqualTo(Type.BOOLEAN);
        assertThat(typeDefiner.defineType(new Object())).isEqualTo(Type.CUSTOM_OBJECT);
    }

    @Test
    @DisplayName("Должен определить неизвестный тип объекта как UNKNOWN")
    public void shouldDefineUnknownTypeObjectAsUNKNOWN() {
        FactoryDefinerType factory = new DefaultFactoryDefinerType(Collections.emptyList());
        TypeDefiner typeDefiner = factory.createDefinerType();
        assertThat(typeDefiner.defineType(1)).isEqualTo(Type.UNKNOWN);
    }

}