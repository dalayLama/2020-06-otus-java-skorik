package ru.otus.testlib.readers;

import ru.otus.testlib.ItemTestResult;
import ru.otus.testlib.MethodResult;
import ru.otus.testlib.TestsResult;

public class ConsoleTestResultsReader implements TestResultsReader {

    @Override
    public void show(TestsResult result) {
        int totalTests = result.getResults().size();
        long success = result.getResults().stream().filter(ItemTestResult::isSuccess).count();
        System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print(String.format("Results tests in \"%s\" class ", result.getTestedClass().getCanonicalName()));
        System.out.println(String.format("total numbers of tests %d, success %d, fails: %d", totalTests, success, totalTests - success));
        int testNumber = 1;
        for (ItemTestResult itemResult : result.getResults()) {
            show(testNumber++, itemResult);
        }
    }

    private void show(int number, ItemTestResult itemTest) {
        System.out.println(String.format("--- Results test #%d", number));
        for (MethodResult methodResult : itemTest.getMethodsResult()) {
            show(methodResult);
        }
        System.out.println("\n\n");
    }

    private void show(MethodResult methodResult) {
        System.out.println(String.format("------ Method \"%s\": %b; Message: %s",
                methodResult.getMethodCaption(),
                methodResult.isSuccess(),
                methodResult.getMessage()));
    }

}
