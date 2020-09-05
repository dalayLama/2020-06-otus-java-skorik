package ru.otus.processor;

import java.time.LocalTime;
import java.util.function.Supplier;

public class SystemSecondSupplier implements Supplier<Integer> {

    @Override
    public Integer get() {
        return LocalTime.now().getSecond();
    }
}
