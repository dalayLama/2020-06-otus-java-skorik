package ru.otus.exceptions;

public class TestExecuteException extends Exception {

    public TestExecuteException() {
    }

    public TestExecuteException(String message) {
        super(message);
    }

    public TestExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestExecuteException(Throwable cause) {
        super(cause);
    }

    public TestExecuteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
