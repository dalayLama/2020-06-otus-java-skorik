package ru.otus.writers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.WriterDefiner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ArrayJsonWriterTest {

    private ArrayJsonWriter writer;

    @BeforeEach
    public void setUp() {
        WriterDefiner definer = mock(WriterDefiner.class);
        given(definer.getWriter(any())).willReturn(new SimpleJsonWriter());
        writer = new ArrayJsonWriter(definer);
    }

    @Test
    @DisplayName("Должен создать правильно оформленный елемент из массива byte")
    public void shouldCreateCorrectElementFromArrayByte() {
        final String expectedResult = "[1, 2, 3]";
        final byte[] object = {1, 2, 3};
        String result = writer.toJson(object);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Должен создать правильно оформленный елемент из массива short")
    public void shouldCreateCorrectElementFromArrayShort() {
        final String expectedResult = "[1, 2, 3]";
        final short[] object = {1, 2, 3};
        String result = writer.toJson(object);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Должен создать правильно оформленный елемент из массива int")
    public void shouldCreateCorrectElementFromArrayInt() {
        final String expectedResult = "[1, 2, 3]";
        final int[] object = {1, 2, 3};
        String result = writer.toJson(object);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Должен создать правильно оформленный елемент из массива long")
    public void shouldCreateCorrectElementFromArrayLong() {
        final String expectedResult = "[1, 2, 3]";
        final long[] object = {1L, 2L, 3L};
        String result = writer.toJson(object);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Должен создать правильно оформленный елемент из массива float")
    public void shouldCreateCorrectElementFromArrayFloat() {
        final String expectedResult = "[1.2, 2.1, 3.045]";
        final float[] object = {1.2f, 2.1f, 3.045f};
        String result = writer.toJson(object);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Должен создать правильно оформленный елемент из массива double")
    public void shouldCreateCorrectElementFromArrayDouble() {
        final String expectedResult = "[1.2, 2.1, 3.045]";
        final double[] object = {1.2d, 2.1d, 3.045d};
        String result = writer.toJson(object);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Должен создать правильно оформленный елемент из массива boolean")
    public void shouldCreateCorrectElementFromArrayBoolean() {
        final String expectedResult = "[true, false, true]";
        final boolean[] object = {true, false, true};
        String result = writer.toJson(object);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Должен создать правильно оформленный елемент из массива characters")
    public void shouldCreateCorrectElementFromArrayCharacters() {
        final String expectedResult = "[a, b, c]";
        final char[] object = {'a', 'b', 'c'};
        String result = writer.toJson(object);
        assertThat(result).isEqualTo(expectedResult);
    }

}