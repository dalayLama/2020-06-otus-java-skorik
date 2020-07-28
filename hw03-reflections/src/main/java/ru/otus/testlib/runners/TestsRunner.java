package ru.otus.testlib.runners;

import ru.otus.testlib.runners.exceptions.RunTestsException;

public interface TestsRunner {

    void runTests(Class<?> clazz) throws RunTestsException, NullPointerException;

}
