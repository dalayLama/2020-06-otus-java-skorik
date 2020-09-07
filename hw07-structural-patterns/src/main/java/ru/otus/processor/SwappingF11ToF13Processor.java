package ru.otus.processor;

import ru.otus.Message;

public class SwappingF11ToF13Processor implements Processor {

    @Override
    public Message process(Message message) {
        return message.toBuilder()
                .field13(message.getField11())
                .field11(message.getField13())
                .build();
    }

}
