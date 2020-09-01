package ru.otus.handler;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.Message;
import ru.otus.listener.*;
import ru.otus.processor.OddTimeChecker;
import ru.otus.processor.Processor;
import ru.otus.processor.SwappingF11ToF13Processor;
import ru.otus.processor.ThrowExceptionProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ComplexProcessorTest {

    @Test
    @DisplayName("Тестируем последовательность вызова процессоров")
    void handleProcessorsTest() {
        //given
        var message = new Message.Builder().field7("field7").build();

        var processor1 = mock(Processor.class);
        when(processor1.process(eq(message))).thenReturn(message);

        var processor2 = mock(Processor.class);
        when(processor2.process(eq(message))).thenReturn(message);

        var processors = List.of(processor1, processor2);

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
        });

        //when
        var result = complexProcessor.handle(message);

        //then
        verify(processor1, times(1)).process(eq(message));
        verify(processor2, times(1)).process(eq(message));
        assertThat(result).isEqualTo(message);
    }

    @Test
    @DisplayName("Тестируем обработку исключения")
    void handleExceptionTest() {
        //given
        var message = new Message.Builder().field8("field8").build();

        var processor1 = mock(Processor.class);
        when(processor1.process(eq(message))).thenThrow(new RuntimeException("Test Exception"));

        var processor2 = mock(Processor.class);
        when(processor2.process(eq(message))).thenReturn(message);

        var processors = List.of(processor1, processor2);

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
            throw new TestException(ex.getMessage());
        });

        //when
        assertThatExceptionOfType(TestException.class).isThrownBy(() -> complexProcessor.handle(message));

        //then
        verify(processor1, times(1)).process(eq(message));
        verify(processor2, never()).process(eq(message));
    }

    @Test
    @DisplayName("Тестируем уведомления")
    void notifyTest() {
        //given
        var message = new Message.Builder().field9("field9").build();

        var listener = mock(Listener.class);

        var complexProcessor = new ComplexProcessor(new ArrayList<>(), (ex) -> {
        });

        complexProcessor.addListener(listener);

        //when
        complexProcessor.handle(message);
        complexProcessor.removeListener(listener);
        complexProcessor.handle(message);

        //then
        verify(listener, times(1)).onUpdated(eq(message), eq(message));
    }

    @Test
    @DisplayName("Тестируем процессор замены field11 на field13")
    void shouldSwapF11ToF13() {
        SwappingF11ToF13Processor p = new SwappingF11ToF13Processor();
        var complexProcessor = new ComplexProcessor(List.of(p), (ex) -> {});
        Message sourceMessage = new Message.Builder().field11("f11").field13("f13").build();
        Message result = complexProcessor.handle(sourceMessage);

        assertThat(result.getField11()).isEqualTo(sourceMessage.getField13());
        assertThat(result.getField13()).isEqualTo(sourceMessage.getField11());
    }

    @Test
    @DisplayName("Тестируем процессор, который должен выбрасывать исключение в четную секунду")
    void shouldThrowExceptionInOddSecond() {
        ThrowExceptionProcessor p = new ThrowExceptionProcessor(new OddTimeChecker());
        var complexProcessor = new ComplexProcessor(List.of(p), (ex) -> System.out.println(ex.toString()));
        Message sourceMessage = new Message.Builder().build();
        complexProcessor.handle(sourceMessage);
    }

    @Test
    @DisplayName("Тестируем листенер, который сохранять сообщения")
    void shouldSaveThrewMessages() {
        CommitsMessageStorage storage = new InMemoryCommitsMessageStorage();
        SaveHistoryMessageListener listener = new SaveHistoryMessageListener(storage);
        SwappingF11ToF13Processor swapProcessor = new SwappingF11ToF13Processor();
        Message.Builder builder = new Message.Builder();
        var complexProcessor = new ComplexProcessor(List.of(swapProcessor), (ex) -> {});
        complexProcessor.addListener(listener);

        List<Message> messages = List.of(
                builder.field11("783").field13("666").build(),
                builder.field11("111").field13("888").build(),
                builder.field11("907").field13("142").build());

        List<Message> resultMessages = messages.stream()
                .map(complexProcessor::handle)
                .collect(Collectors.toList());

        List<CommitMessage> commits = new ArrayList<>(storage.getAll());
        assertThat(commits.size()).isEqualTo(messages.size());
        for (int i = 0; i < messages.size(); i++) {
            Message sourceMessage = messages.get(i);
            Message resultMessage = resultMessages.get(i);
            CommitMessage commit = commits.get(i);

            assertThat(commit.getOldMessage()).isEqualTo(sourceMessage);
            assertThat(commit.getNewMessage()).isEqualTo(resultMessage);
        }
    }


    private static class TestException extends RuntimeException {
        public TestException(String message) {
            super(message);
        }
    }
}