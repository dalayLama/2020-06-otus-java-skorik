package ru.otus.tests;

import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class ExampleTest2 {

    @Before
    public void before() {
        System.out.println("BEFORE");
        System.out.println("---------");
    }

//    @After
//    public void after() throws Exception {
//        throw new Exception("uuu");
//    }
//
    @Test
    public void simpleTest2() {
        System.out.println("TEST");
        System.out.println("---------");
    }

}
