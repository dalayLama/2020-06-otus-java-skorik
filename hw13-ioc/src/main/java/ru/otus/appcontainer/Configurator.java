package ru.otus.appcontainer;

import java.util.*;

public class Configurator {

    private final Object objectConfigurator;

    private final int order;

    private final List<MethodConfig> configs;

    public Configurator(Object objectConfigurator, int order, Collection<MethodConfig> configs) {
        this.objectConfigurator = objectConfigurator;
        this.order = order;
        this.configs = new ArrayList<>(configs);
        this.configs.sort(Comparator.comparingInt(MethodConfig::getOrder));
    }

    public Object getObjectConfigurator() {
        return objectConfigurator;
    }

    public int getOrder() {
        return order;
    }

    public List<MethodConfig> getConfigs() {
        return Collections.unmodifiableList(configs);
    }
}
