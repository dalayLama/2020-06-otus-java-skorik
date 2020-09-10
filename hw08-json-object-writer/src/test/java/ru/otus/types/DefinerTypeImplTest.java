package ru.otus.types;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.WritableType;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class DefinerTypeImplTest {

    @Test
    @DisplayName("Должен правильно определить типы объектов")
    public void shouldDefineTypesCorrect() {
        FactoryDefinerType factory = new DefaultFactoryDefinerType();
        DefinerType definerType = factory.createDefinerType();

        assertThat(definerType.defineType(1)).isEqualTo(WritableType.NUMBER);
        assertThat(definerType.defineType("s")).isEqualTo(WritableType.STRING);
        assertThat(definerType.defineType('c')).isEqualTo(WritableType.CHAR);
        assertThat(definerType.defineType(null)).isEqualTo(WritableType.NULL);
        assertThat(definerType.defineType(new ArrayList<>())).isEqualTo(WritableType.ITERABLE);
        assertThat(definerType.defineType(new Object[0])).isEqualTo(WritableType.ARRAY);
        assertThat(definerType.defineType(true)).isEqualTo(WritableType.BOOLEAN);
        assertThat(definerType.defineType(new Object())).isEqualTo(WritableType.CUSTOM_OBJECT);
    }

    @Test
    @DisplayName("Должен определить неизвестный тип объекта как UNKNOWN")
    public void shouldDefineUnknownTypeObjectAsUNKNOWN() {
        FactoryDefinerType factory = new DefaultFactoryDefinerType(Collections.emptyList());
        DefinerType definerType = factory.createDefinerType();
        assertThat(definerType.defineType(1)).isEqualTo(WritableType.UNKNOWN);
    }

}