package ru.otus.processor;

import ru.otus.Message;

public class ThrowExceptionProcessor implements Processor {

    private final MessageChecker checker;

    public ThrowExceptionProcessor(MessageChecker checker) {
        this.checker = checker;
    }

    public ThrowExceptionProcessor() {
        this.checker = new OddTimeChecker();
    }

    @Override
    public Message process(Message message) {
        if (!checker.isOk(message)) {
            checker.throwException();
        }
        return message;
    }
}
