package ru.otus.testlib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TestsResult {

    private final Class<?> testedClass;

    private final List<ItemTestResult> results = new ArrayList<>();

    public TestsResult(Class<?> testedClass, Collection<? extends ItemTestResult> results) {
        this.testedClass = testedClass;
        this.results.addAll(results);
    }

    public Class<?> getTestedClass() {
        return testedClass;
    }

    public List<ItemTestResult> getResults() {
        return Collections.unmodifiableList(results);
    }
}
