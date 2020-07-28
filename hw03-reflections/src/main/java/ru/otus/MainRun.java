package ru.otus;

import ru.otus.exceptions.RunTestsException;
import ru.otus.tests.ExampleTest;
import ru.otus.tests.ExampleTest2;

public class MainRun {

    public static void main(String[] args) throws RunTestsException {
        TestRunners.runTest(ExampleTest.class);
        TestRunners.runTest(ExampleTest2.class);
    }

}
