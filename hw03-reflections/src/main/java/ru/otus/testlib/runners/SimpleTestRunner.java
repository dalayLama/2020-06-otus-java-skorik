package ru.otus.testlib.runners;

import ru.otus.testlib.executors.context.TestContext;
import ru.otus.testlib.executors.context.exceptions.ContextCreateException;
import ru.otus.testlib.runners.exceptions.RunTestsException;
import ru.otus.testlib.executors.exceptions.TestExecuteException;
import ru.otus.testlib.TestsResult;
import ru.otus.testlib.executors.context.ContextCreator;
import ru.otus.testlib.executors.TestExecutor;
import ru.otus.testlib.readers.TestResultsReader;

public class SimpleTestRunner implements TestsRunner {

    private final ContextCreator contextCreator;

    private final TestExecutor executor;

    private final TestResultsReader resultsReader;

    public SimpleTestRunner(ContextCreator contextCreator, TestExecutor executor, TestResultsReader resultsReader) {
        this.contextCreator = contextCreator;
        this.executor = executor;
        this.resultsReader = resultsReader;
    }

    @Override
    public void runTests(Class<?> clazz) throws RunTestsException, NullPointerException {
        if (clazz == null) {
            throw new NullPointerException();
        }
        try {
            TestContext context = contextCreator.createContext(clazz);
            TestsResult result = executor.execute(context);
            resultsReader.show(result);
        } catch (ContextCreateException | TestExecuteException e) {
            throw new RunTestsException(e);
        }
    }



}
