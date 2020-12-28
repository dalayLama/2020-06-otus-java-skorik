package ru.otus.appcontainer;

import java.lang.reflect.Method;

public class MethodConfig {

    private final int order;

    private final String componentName;

    private final Method method;

    public MethodConfig(int order, String componentName, Method method) {
        this.order = order;
        this.componentName = componentName;
        this.method = method;
    }

    public int getOrder() {
        return order;
    }

    public String getComponentName() {
        return componentName;
    }

    public Method getMethod() {
        return method;
    }
}
