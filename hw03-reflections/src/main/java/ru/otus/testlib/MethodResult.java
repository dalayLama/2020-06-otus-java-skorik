package ru.otus.testlib;

public class MethodResult {

    private final String methodCaption;

    private final boolean success;

    private final String message;

    private final boolean test;

    public MethodResult(String methodCaption, boolean success, String message, boolean test) {
        this.methodCaption = methodCaption;
        this.success = success;
        this.message = message;
        this.test = test;
    }

    public String getMethodCaption() {
        return methodCaption;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isTest() {
        return test;
    }
}
