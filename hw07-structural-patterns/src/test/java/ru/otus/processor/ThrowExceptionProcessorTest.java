package ru.otus.processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import ru.otus.Message;
import ru.otus.processor.exceptions.CheckerException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ThrowExceptionProcessorTest {

    private static final Message ANY_MESSAGE = new Message.Builder().build();

    @Test
    @DisplayName("проверяем, что exception срабатывает")
    void shouldThrowException() {
        MessageChecker checker = mock(MessageChecker.class);
        given(checker.isOk(ANY_MESSAGE)).willReturn(false);
        BDDMockito.doThrow(new CheckerException()).when(checker).throwException();

        ThrowExceptionProcessor processor = new ThrowExceptionProcessor(checker);
        org.junit.jupiter.api.Assertions.assertThrows(CheckerException.class, () ->
                processor.process(ANY_MESSAGE));
    }

    @Test
    @DisplayName("проверяем, что exception не срабатывает")
    void shouldNotThrowException() {
        MessageChecker checker = mock(MessageChecker.class);
        given(checker.isOk(ANY_MESSAGE)).willReturn(true);

        ThrowExceptionProcessor processor = new ThrowExceptionProcessor(checker);
        processor.process(ANY_MESSAGE);
    }

    @Test
    @DisplayName("проверяем, что процесс возвращает исходное сообщение")
    void shouldReturnTheSameMessage() {
        MessageChecker checker = mock(MessageChecker.class);
        given(checker.isOk(ANY_MESSAGE)).willReturn(true);

        ThrowExceptionProcessor processor = new ThrowExceptionProcessor(checker);
        Message resultMessage = processor.process(ANY_MESSAGE);
        assertThat(resultMessage).isSameAs(ANY_MESSAGE);
    }

}