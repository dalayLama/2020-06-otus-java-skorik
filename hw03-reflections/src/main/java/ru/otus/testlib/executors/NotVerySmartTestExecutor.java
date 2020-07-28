package ru.otus.testlib.executors;

import ru.otus.exceptions.TestExecuteException;
import ru.otus.testlib.*;
import ru.otus.testlib.executors.context.InfoMethod;
import ru.otus.testlib.executors.context.TestContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class NotVerySmartTestExecutor implements TestExecutor {

    @Override
    public TestsResult execute(TestContext context) throws TestExecuteException {
        List<ItemTestResult> results = new ArrayList<>(context.getMethodsTests().size());
        for (InfoMethod testMethod : context.getMethodsTests()) {
            results.add(runTest(context, testMethod));
        }
        return new TestsResult(context.getClazz(), results);
    }

    private ItemTestResult runTest(TestContext testContext, InfoMethod testMethod) throws TestExecuteException {
        TestExecuteContext exContext = new TestExecuteContext(newInstance(testContext.getConstructor()));

        if (testContext.getMethodBefore() != null) {
            runMethod(testContext.getMethodBefore(), exContext);
        }

        runMethod(testMethod, exContext);

        if (testContext.getMethodAfter() != null) {
            runMethod(testContext.getMethodAfter(), exContext);
        }

        return exContext.buildResult();
    }

    private void runMethod(InfoMethod infoMethod, TestExecuteContext context) throws TestExecuteException {
        try {
            infoMethod.getMethod().invoke(context.getTestInstance());
            context.addResult(infoMethod, true);
        } catch (InvocationTargetException e) {
            context.addResult(infoMethod, false, e.getTargetException().getMessage());
        } catch (IllegalAccessException e) {
            throw new TestExecuteException(e);
        }
    }

    private Object newInstance(Constructor<?> constructor) throws TestExecuteException {
        try {
            return constructor.newInstance();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new TestExecuteException(e);
        }
    }

    private static class TestExecuteContext {

        private final Object testInstance;

        private final List<MethodResult> methodsResults = new ArrayList<>(3);

        private boolean testMethodSuccess = false;

        public TestExecuteContext(Object testInstance) {
            this.testInstance = testInstance;
        }

        public Object getTestInstance() {
            return testInstance;
        }

        public boolean isTestMethodSuccess() {
            return testMethodSuccess;
        }

        public void setTestMethodSuccess(boolean testMethodSuccess) {
            this.testMethodSuccess = testMethodSuccess;
        }

        public void addResult(InfoMethod infoMethod, boolean success, String message) {
            methodsResults.add(new MethodResult(infoMethod.getCaption(), success, message, infoMethod.isTest()));
            if (infoMethod.isTest()) {
                setTestMethodSuccess(success);
            }
        }

        public void addResult(InfoMethod infoMethod, boolean success) {
            addResult(infoMethod, success, null);
        }

        public ItemTestResult buildResult() {
            return new ItemTestResult(testMethodSuccess, methodsResults);
        }
    }

}
