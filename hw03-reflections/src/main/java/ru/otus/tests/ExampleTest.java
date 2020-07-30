package ru.otus.tests;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.DisplayName;
import ru.otus.annotations.Test;

public class ExampleTest {

    private int testVariable;

    @Before
    public void before() {
        testVariable = 53;
        System.out.println("BEFORE");
        System.out.println(String.format("current hashcode: %s", this.hashCode()));
        System.out.println(String.format("variable value: %d", testVariable));
        System.out.println("---------");
    }

    @After
    public void after() {
        System.out.println("AFTER");
        System.out.println(String.format("current hashcode: %s", this.hashCode()));
        System.out.println(String.format("variable value: %d", testVariable));
        System.out.println("---------");
    }

    @Test
    public void testChangeVariable() {
        System.out.println("TEST");
        System.out.println(String.format("current hashcode: %s", this.hashCode()));
        System.out.println(String.format("variable value: %d", testVariable));
        testVariable = 87;
        System.out.println(String.format("change value variable to value: %d", testVariable));
        System.out.println("---------");
    }

    @Test
    @DisplayName(caption = "тест аннотации display name")
    public void simpleTest2() {
        System.out.println("TEST");
        System.out.println("---------");
    }

    @Test
    @DisplayName(caption = "тест падения")
    public void checkedException() throws Exception {
        System.out.println("TEST");
        System.out.println("---------");
        throw new Exception("no message");
    }

    @Test
    public void uncheckedException() {
        System.out.println("TEST");
        System.out.println("---------");
        throw new RuntimeException("no message");
    }

}
