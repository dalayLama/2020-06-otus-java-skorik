package ru.otus.testlib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * the result of execute one test(include his before and after methods)
 */
public class ItemTestResult {

    private final boolean success;

    private final List<MethodResult> methodsResult = new ArrayList<>();

    public ItemTestResult(boolean success, Collection<? extends MethodResult> methodsResults) {
        this.success = success;
        this.methodsResult.addAll(methodsResults);
    }

    public boolean isSuccess() {
        return success;
    }

    public List<MethodResult> getMethodsResult() {
        return Collections.unmodifiableList(methodsResult);
    }
}
