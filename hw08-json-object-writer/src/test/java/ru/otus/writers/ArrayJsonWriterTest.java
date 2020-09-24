package ru.otus.writers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.WriterDefiner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ArrayJsonWriterTest {

    @Test
    @DisplayName("Должен создать правильно оформленный елемент из массива")
    public void shouldCreateCorrectElementFromArray() {
        final String expectedResult = "[1, 2, 3]";
        final int[] object = {1, 2, 3};

        WriterDefiner definer = mock(WriterDefiner.class);
        given(definer.getWriter(any())).willReturn(new SimpleJsonWriter());

        ArrayJsonWriter writer = new ArrayJsonWriter(definer);
        String result = writer.toJson(object);
        assertThat(result).isEqualTo(expectedResult);
    }

}