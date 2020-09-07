package ru.otus.processor.exceptions;

public class TimeToDieException extends RuntimeException {

    private final int secondDie;

    public TimeToDieException(int currentSecond) {
        super(String.format("I died at %d second", currentSecond));
        this.secondDie = currentSecond;
    }

    public int getSecondDie() {
        return secondDie;
    }

}
