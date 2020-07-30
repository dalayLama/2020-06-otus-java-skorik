package ru.otus.testlib.executors.context;

import java.lang.reflect.Method;

public class InfoMethod {

    private final String caption;

    private final Method method;

    private final boolean test;

    public InfoMethod(String caption, Method method, boolean test) {
        this.caption = caption;
        this.method = method;
        this.test = test;
    }

    public InfoMethod(Method method, boolean test) {
        this.method = method;
        this.caption = method.getName();
        this.test = test;
    }

    public String getCaption() {
        return caption;
    }

    public Method getMethod() {
        return method;
    }

    public boolean isTest() {
        return test;
    }
}
