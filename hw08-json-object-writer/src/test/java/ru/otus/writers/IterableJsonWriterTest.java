package ru.otus.writers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.WriterDefiner;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class IterableJsonWriterTest {

    private int counter = 1;

    @Test
    @DisplayName("Должен создать правильно оформленный елемент из List-а")
    public void shouldCreateCorrectElementFromList() {
        final String expectedResult = "[1, 2, 3]";
        final List<Integer> object = List.of(1, 2, 3);

        WriterDefiner definer = mock(WriterDefiner.class);
        given(definer.getWriter(any())).willReturn(new SimpleJsonWriter());

        IterableJsonWriter writer = new IterableJsonWriter(definer);
        String result = writer.toJson(object);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Должен создать правильно оформленные елемент из Set-а")
    public void shouldCreateCorrectElementFromSet() {
        final String expectedResult = "[1, 2, 3]";
        final Set<Integer> object = Set.of(1, 2, 3);

        WriterDefiner definer = mock(WriterDefiner.class);
        given(definer.getWriter(any())).willReturn(new SimpleJsonWriter());

        IterableJsonWriter writer = new IterableJsonWriter(definer);
        String result = writer.toJson(object);
        assertThat(result).isEqualTo(expectedResult);
    }

}