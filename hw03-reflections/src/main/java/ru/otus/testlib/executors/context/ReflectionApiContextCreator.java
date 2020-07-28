package ru.otus.testlib.executors.context;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.DisplayName;
import ru.otus.annotations.Test;
import ru.otus.exceptions.ContextCreateException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class ReflectionApiContextCreator implements ContextCreator {

    @Override
    public TestContext createContext(Class<?> clazz) throws ContextCreateException {
        Constructor<?> constructor = getConstructor(clazz);
        TestContextBuilder contextBuilder = new TestContextBuilder(clazz, constructor);
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                contextBuilder.setMethodBefore(createInfoMethod(method));
            }
            if (method.isAnnotationPresent(Test.class)) {
                contextBuilder.addTestMethod(createInfoMethod(method, true));
            }
            if (method.isAnnotationPresent(After.class)) {
                contextBuilder.setMethodAfter(createInfoMethod(method));
            }
        }
        return contextBuilder.build();
    }

    private InfoMethod createInfoMethod(Method method) {
        return createInfoMethod(method, false);
    }

    private InfoMethod createInfoMethod(Method method, boolean test) {
        DisplayName annotation = method.getAnnotation(DisplayName.class);
        return annotation != null ? new InfoMethod(annotation.caption(), method, test) : new InfoMethod(method, test);
    }

    private Constructor<?> getConstructor(Class<?> clazz) throws ContextCreateException {
        if (clazz == null) {
            throw new NullPointerException();
        }
        try {
            return clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            String message = String.format("\"%s\" has to have constructor without parameters", clazz.getCanonicalName());
            throw new ContextCreateException(message, e);
        }
    }

    private static class TestContextBuilder {

        private InfoMethod methodBefore;

        private InfoMethod methodAfter;

        private final Class<?> clazz;

        private final Constructor<?> constructor;

        private final Set<InfoMethod> testMethods = new HashSet<>();

        TestContextBuilder(Class<?> clazz, Constructor<?> constructor) {
            this.clazz = clazz;
            this.constructor = constructor;
        }

        TestContextBuilder setMethodBefore(InfoMethod methodBefore) throws ContextCreateException {
            if (this.methodBefore != null) {
                throw new ContextCreateException("The beforeMethod must be only one");
            } else if (this.methodAfter == methodBefore) {
                throw new ContextCreateException("the beforeMethod and afterMethod must not be the same");
            } else if (testMethods.contains(methodBefore)) {
                throw new ContextCreateException("the beforeMethod and testMethod must not be the same");
            }
            this.methodBefore = methodBefore;
            return this;
        }

        TestContextBuilder setMethodAfter(InfoMethod methodAfter) throws ContextCreateException {
            if (this.methodAfter != null) {
                throw new ContextCreateException("The afterMethod must be only one");
            } else if (this.methodBefore == methodAfter) {
                throw new ContextCreateException("the beforeMethod and afterMethod must not be the same");
            } else if (testMethods.contains(methodAfter)) {
                throw new ContextCreateException("the afterMethod and testMethod must not be the same");
            }
            this.methodAfter = methodAfter;
            return this;
        }

        TestContextBuilder addTestMethod(InfoMethod testMethod) throws ContextCreateException {
            if (this.methodBefore == testMethod) {
                throw new ContextCreateException("the beforeMethod and testMethod must not be the same");
            } else if (this.methodAfter == testMethod) {
                throw new ContextCreateException("the afterMethod and testMethod must not be the same");
            }
            this.testMethods.add(testMethod);
            return this;
        }

        TestContext build() {
            TestContextImpl impl = new TestContextImpl();
            impl.setClazz(clazz);
            impl.setConstructor(constructor);
            impl.setMethodBefore(methodBefore);
            impl.setMethodAfter(methodAfter);
            impl.setTestMethods(testMethods);
            return impl;
        }

    }

    private static class TestContextImpl implements TestContext {

        private Class<?> clazz;

        private Constructor<?> constructor;

        private InfoMethod methodBefore;

        private Set<InfoMethod> testMethods;

        private InfoMethod methodAfter;

        @Override
        public Class<?> getClazz() {
            return clazz;
        }

        @Override
        public Constructor<?> getConstructor() {
            return constructor;
        }

        @Override
        public InfoMethod getMethodBefore() {
            return methodBefore;
        }

        @Override
        public Set<InfoMethod> getMethodsTests() {
            return testMethods;
        }

        @Override
        public InfoMethod getMethodAfter() {
            return methodAfter;
        }

        public void setClazz(Class<?> clazz) {
            this.clazz = clazz;
        }

        public void setConstructor(Constructor<?> constructor) {
            this.constructor = constructor;
        }

        public void setMethodBefore(InfoMethod methodBefore) {
            this.methodBefore = methodBefore;
        }

        public void setTestMethods(Set<InfoMethod> testMethods) {
            this.testMethods = testMethods;
        }

        public void setMethodAfter(InfoMethod methodAfter) {
            this.methodAfter = methodAfter;
        }
    }

}
