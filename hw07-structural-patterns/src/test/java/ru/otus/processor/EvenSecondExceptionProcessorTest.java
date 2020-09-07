package ru.otus.processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.Message;
import ru.otus.processor.exceptions.TimeToDieException;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class EvenSecondExceptionProcessorTest {

    private static final Message ANY_MESSAGE = new Message.Builder().build();

    @SuppressWarnings("rawtypes")
    @Test
    @DisplayName("проверяем, что exception срабатывает")
    void shouldThrowException() {
        Supplier secondSupplier = mock(Supplier.class);
        given(secondSupplier.get()).willReturn(2);

        EvenSecondExceptionProcessor processor = new EvenSecondExceptionProcessor(secondSupplier);
        org.junit.jupiter.api.Assertions.assertThrows(TimeToDieException.class, () ->
                processor.process(ANY_MESSAGE));
    }

    @SuppressWarnings("rawtypes")
    @Test
    @DisplayName("проверяем, что exception не срабатывает")
    void shouldNotThrowException() {
        Supplier secondSupplier = mock(Supplier.class);
        given(secondSupplier.get()).willReturn(1);

        EvenSecondExceptionProcessor processor = new EvenSecondExceptionProcessor(secondSupplier);
        processor.process(ANY_MESSAGE);
    }

    @Test
    @DisplayName("проверяем, что процесс возвращает исходное сообщение")
    void shouldReturnTheSameMessage() {
        Supplier secondSupplier = mock(Supplier.class);
        given(secondSupplier.get()).willReturn(1);

        EvenSecondExceptionProcessor processor = new EvenSecondExceptionProcessor(secondSupplier);
        Message resultMessage = processor.process(ANY_MESSAGE);
        assertThat(resultMessage).isSameAs(ANY_MESSAGE);
    }

}