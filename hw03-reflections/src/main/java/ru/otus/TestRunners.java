package ru.otus;

import ru.otus.testlib.runners.exceptions.RunTestsException;
import ru.otus.testlib.executors.NotVerySmartTestExecutor;
import ru.otus.testlib.executors.TestExecutor;
import ru.otus.testlib.executors.context.ContextCreator;
import ru.otus.testlib.executors.context.ReflectionApiContextCreator;
import ru.otus.testlib.readers.ConsoleTestResultsReader;
import ru.otus.testlib.readers.TestResultsReader;
import ru.otus.testlib.runners.SimpleTestRunner;
import ru.otus.testlib.runners.TestsRunner;

public class TestRunners {

    private static TestsRunner testsRunner;

    static {
        ContextCreator contextCreator = new ReflectionApiContextCreator();
        TestExecutor executor = new NotVerySmartTestExecutor();
        TestResultsReader reader = new ConsoleTestResultsReader();
        testsRunner =  new SimpleTestRunner(contextCreator, executor, reader);
    }

    public static void runTest(Class<?> clazz) throws RunTestsException {
        testsRunner.runTests(clazz);
    }

}
