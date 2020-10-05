package ru.otus.hibernate;

import org.flywaydb.core.Flyway;
import org.hibernate.cfg.Configuration;
import ru.otus.db.MigrationManager;
import ru.otus.db.exceptions.MigrateException;

public class HibernateFlywayMigrationManager implements MigrationManager {

    private final static String DB_URL_PROPERTY = "hibernate.connection.url";

    private final static String DB_USER_NAME_PROPERTY = "hibernate.connection.username";

    private final static String DB_PASSWORD_PROPERTY = "hibernate.connection.password";

    private final Configuration configuration;

    private final String locationMigrations;

    public HibernateFlywayMigrationManager(Configuration configuration, String locationMigrations) {
        this.configuration = configuration;
        this.locationMigrations = locationMigrations;
    }

    @Override
    public void migrate() throws MigrateException {
        String dbUrl = configuration.getProperty(DB_URL_PROPERTY);
        String dbUser = configuration.getProperty(DB_USER_NAME_PROPERTY);
        String dbPassword = configuration.getProperty(DB_PASSWORD_PROPERTY);

        try {
            var flyway = Flyway.configure()
                    .dataSource(dbUrl, dbUser, dbPassword)
                    .locations(locationMigrations)
                    .load();
            flyway.migrate();
        } catch (Exception e) {
            throw new MigrateException(e);
        }
    }
}
