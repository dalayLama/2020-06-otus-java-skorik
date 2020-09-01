package ru.otus.processor.exceptions;

public class TimeToDieException extends CheckerException {

    private final int secondDie;

    public TimeToDieException(int currentSecond) {
        this.secondDie = currentSecond;
    }

    public int getSecondDie() {
        return secondDie;
    }

    @Override
    public String toString() {
        return String.format("I died at %d second", secondDie);
    }
}
