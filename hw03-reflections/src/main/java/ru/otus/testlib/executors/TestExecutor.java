package ru.otus.testlib.executors;

import ru.otus.testlib.executors.context.TestContext;
import ru.otus.testlib.TestsResult;
import ru.otus.testlib.executors.exceptions.TestExecuteException;

public interface TestExecutor {

    TestsResult execute(TestContext context) throws TestExecuteException;

}
