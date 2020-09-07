package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.InMemoryCommitsMessageStorage;
import ru.otus.listener.SaveHistoryMessageListener;
import ru.otus.processor.EvenSecondExceptionProcessor;
import ru.otus.processor.SwappingF11ToF13Processor;
import ru.otus.processor.SystemSecondSupplier;

import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13
       2. Сделать процессор, который поменяет местами значения field11 и field13
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду
       4. Сделать Listener для ведения истории: старое сообщение - новое
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */
        var swapProcessor = new SwappingF11ToF13Processor();
        var timeExceptionProcessor = new EvenSecondExceptionProcessor(new SystemSecondSupplier());
        var listener = new SaveHistoryMessageListener(new InMemoryCommitsMessageStorage());

        var complexProcessor = new ComplexProcessor(List.of(swapProcessor, timeExceptionProcessor),
                (ex) -> System.out.println(ex.getMessage()));
        complexProcessor.addListener(listener);

        Message message = new Message.Builder().field11("783").field13("666").build();
        Message result = complexProcessor.handle(message);

        System.out.println(result);

        complexProcessor.removeListener(listener);
    }
}
