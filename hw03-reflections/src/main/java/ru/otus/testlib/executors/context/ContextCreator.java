package ru.otus.testlib.executors.context;

import ru.otus.testlib.executors.context.exceptions.ContextCreateException;

public interface ContextCreator {

    TestContext createContext(Class<?> clazz) throws ContextCreateException, NullPointerException;

}
