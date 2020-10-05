package ru.otus.hibernate;

import org.hibernate.cfg.Configuration;
import ru.otus.hibernate.exceptions.CreatingConfigurationException;

public interface FactoryConfigurationHibernate {

    Configuration createConfiguration() throws CreatingConfigurationException;

}
