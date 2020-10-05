package ru.otus.hibernate;

import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import ru.otus.hibernate.exceptions.CreatingConfigurationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FactoryConfigurationHibernateByResourceTest {

    private static final String PATH_TO_RESOURCE = "hibernate.cfg.xml";

    @Test
    public void shouldCreateConfiguration() {
        final String pathToResource = "hibernate.cfg.xml";
        final String checkedProperty = "hibernate.connection.url";
        final String expectedCheckedValueProperty = "jdbc:h2:mem:OtusExamplesDB;DB_CLOSE_DELAY=-1";

        FactoryConfigurationHibernateByResource factory =
                new FactoryConfigurationHibernateByResource(pathToResource);
        Configuration configuration = factory.createConfiguration();
        assertThat(configuration).isNotNull();
        assertThat(configuration.getProperty(checkedProperty)).isEqualTo(expectedCheckedValueProperty);
    }

    @Test
    public void shouldThrowExceptionBecauseOfNotExistingResource() {
        FactoryConfigurationHibernateByResource factory =
                new FactoryConfigurationHibernateByResource("-");
        assertThrows(CreatingConfigurationException.class, factory::createConfiguration);
    }

}