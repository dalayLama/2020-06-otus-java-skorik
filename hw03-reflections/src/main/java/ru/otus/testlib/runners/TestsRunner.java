package ru.otus.testlib.runners;

import ru.otus.exceptions.RunTestsException;

public interface TestsRunner {

    void runTests(Class<?> clazz) throws RunTestsException, NullPointerException;

}
