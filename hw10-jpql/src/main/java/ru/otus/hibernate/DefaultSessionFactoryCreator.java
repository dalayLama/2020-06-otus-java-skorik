package ru.otus.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultSessionFactoryCreator implements SessionFactoryCreator {

    private final Configuration configuration;

    private final List<Class<?>> entityClasses = new ArrayList<>();

    public DefaultSessionFactoryCreator(Configuration configuration) {
        this.configuration = configuration;
    }

    public void addEntityClasses(Class<?>... classes) {
        entityClasses.addAll(Arrays.asList(classes));
    }

    @Override
    public SessionFactory create() {
        var serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        var metadata = new MetadataSources(serviceRegistry);
        entityClasses.forEach(metadata::addAnnotatedClass);
        return metadata.getMetadataBuilder()
                .build()
                .getSessionFactoryBuilder()
                .build();
    }
}
