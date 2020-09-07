package ru.otus.processor;

import ru.otus.Message;
import ru.otus.processor.exceptions.TimeToDieException;

import java.util.function.Supplier;

public class EvenSecondExceptionProcessor implements Processor {

    private final Supplier<Integer> secondSupplier;

    public EvenSecondExceptionProcessor(Supplier<Integer> secondSupplier) {
        this.secondSupplier = secondSupplier;
    }

    @Override
    public Message process(Message message) {
        Integer second = secondSupplier.get();
        if (second % 2 == 0) {
            throw new TimeToDieException(second);
        }
        return message;
    }
}
