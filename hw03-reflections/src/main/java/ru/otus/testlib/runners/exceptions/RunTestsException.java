package ru.otus.testlib.runners.exceptions;

public class RunTestsException extends Exception {

    public RunTestsException() {
    }

    public RunTestsException(String message) {
        super(message);
    }

    public RunTestsException(String message, Throwable cause) {
        super(message, cause);
    }

    public RunTestsException(Throwable cause) {
        super(cause);
    }

    public RunTestsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
