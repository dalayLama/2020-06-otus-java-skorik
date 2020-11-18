package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.appcontainer.exceptions.AddingComponentException;
import ru.otus.appcontainer.exceptions.CreatingComponentException;
import ru.otus.appcontainer.exceptions.NotFoundAppComponent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        Object o;
        if (componentClass.isInterface() || Modifier.isAbstract(componentClass.getModifiers())) {
            o = appComponents.stream()
                    .filter(c -> componentClass.isAssignableFrom(c.getClass()))
                    .findFirst().orElse(null);
        } else {
            o = appComponents.stream()
                    .filter(c -> c.getClass().equals(componentClass))
                    .findFirst().orElse(null);
        }
        return (C) o;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        Object objectConfigurator = createObjectConfigurator(configClass);
        int order = configClass.getAnnotation(AppComponentsContainerConfig.class).order();

        List<MethodConfig> configs = createMethodsConfigs(configClass);
        Configurator configurator = new Configurator(objectConfigurator, order, configs);
        loadConfigurator(configurator);
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    private List<MethodConfig> createMethodsConfigs(Class<?> configClass) {
        return Arrays.stream(configClass.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(AppComponent.class))
                .map(m -> {
                    AppComponent annotation = m.getAnnotation(AppComponent.class);
                    return new MethodConfig(annotation.order(), annotation.name(), m);
                })
                .collect(Collectors.toList());
    }

    private void loadConfigurator(Configurator configurator) {
        configurator.getConfigs().forEach(methodConfig -> {
            Object component = createComponent(configurator.getObjectConfigurator(), methodConfig);
            addComponent(methodConfig.getComponentName(), component);
        });
    }

    private Object createComponent(Object objectConfigurator, MethodConfig config) {
        try {
            Object[] parametersImplements = getParametersImplements(config.getMethod().getParameters());
            Object result = config.getMethod().invoke(objectConfigurator, parametersImplements);
            if (Objects.isNull(result)) {
                throw new CreatingComponentException(String.format("Method %s of object %s returned nothing",
                        config.getMethod().getName(), objectConfigurator.getClass().getName()));
            }
            return result;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new CreatingComponentException(e);
        }
    }

    private Object[] getParametersImplements(Parameter[] parameters) {
        Object[] result = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Class<?> type = parameters[i].getType();
            Object appComponent = getAppComponent(type);
            if (Objects.isNull(appComponent)) {
                throw new NotFoundAppComponent(String.format("AppComponent for class %s wasn't found", type.getName()));
            }
            result[i] = appComponent;
        }
        return result;
    }

    private Object createObjectConfigurator(Class<?> classConfig) {
        try {
            Constructor<?> constructor = classConfig.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addComponent(String name, Object component) {
        if (appComponentsByName.containsKey(name)) {
            throw new AddingComponentException(String.format("Duplicate component name %s", name));
        }
        appComponents.add(component);
        appComponentsByName.put(name, component);
    }

}
