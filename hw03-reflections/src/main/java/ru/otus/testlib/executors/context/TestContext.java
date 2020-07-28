package ru.otus.testlib.executors.context;

import java.lang.reflect.Constructor;
import java.util.Set;

public interface TestContext {

    /**
     * the class of testing
     * @return
     */
    Class<?> getClazz();

    /**
     * constructor for create new instances test's
     * @return
     */
    Constructor<?> getConstructor();

    /**
     * information about the method which will be performed before test. can be null
     * @return
     */
    InfoMethod getMethodBefore();

    /**
     * information about the methods which will be performed as test
     * @return
     */
    Set<InfoMethod> getMethodsTests();

    /**
     * information about the method which will be performed after test. can be null
     * @return
     */
    InfoMethod getMethodAfter();

}
