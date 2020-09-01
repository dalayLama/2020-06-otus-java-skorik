package ru.otus.processor.exceptions;

public class TimeToDieException extends CheckerException {

    private final int secondDie;

    public TimeToDieException(int currentSecond) {
        this.secondDie = currentSecond;
    }

    public int getSecondDie() {
        return secondDie;
    }
}
