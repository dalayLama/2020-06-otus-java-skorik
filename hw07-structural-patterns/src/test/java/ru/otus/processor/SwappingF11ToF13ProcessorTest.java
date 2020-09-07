package ru.otus.processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.Message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SwappingF11ToF13ProcessorTest {

    @Test
    @DisplayName("Тестируем правильность замены поля f11 на f13")
    void shouldCorrectSwapF11toF13() {
        Message sourceMessage = new Message.Builder()
                .field11("11")
                .field13("13")
                .build();
        SwappingF11ToF13Processor processor = new SwappingF11ToF13Processor();
        Message processedMessage = processor.process(sourceMessage);

        assertThat(processedMessage.getField11()).isEqualTo(sourceMessage.getField13());
        assertThat(processedMessage.getField13()).isEqualTo(sourceMessage.getField11());
    }

}