package ru.otus.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import ru.otus.cachehw.HwCache;
import ru.otus.cachehw.MyCache;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.Address;
import ru.otus.core.model.Phone;
import ru.otus.core.model.User;
import ru.otus.core.service.CachedUserDaoDBService;
import ru.otus.core.service.UserDBService;
import ru.otus.core.service.UserDaoDBService;
import ru.otus.hibernate.*;

@Configuration
public class AppConfig {

    private static final String PATH_CONFIG_HIBER = "WEB-INF/hibernate.cfg.xml";

    private static final String MIGRATION = "WEB-INF/db/migration";

    @Bean
    org.hibernate.cfg.Configuration configuration() {
        return new org.hibernate.cfg.Configuration().configure(PATH_CONFIG_HIBER);
    }

    @Bean(initMethod = "migrate")
    HibernateFlywayMigrationManager migrationManager(org.hibernate.cfg.Configuration configuration) {
        return new HibernateFlywayMigrationManager(configuration, MIGRATION);
    }

    @Bean
    @DependsOn("migrationManager")
    public SessionFactoryCreator sessionFactoryCreator(org.hibernate.cfg.Configuration config) {
        DefaultSessionFactoryCreator sessionFactoryCreator = new DefaultSessionFactoryCreator(config);
        sessionFactoryCreator.addEntityClasses(User.class, Phone.class, Address.class);
        return sessionFactoryCreator;
    }

    @Bean
    public SessionFactory sessionFactory(SessionFactoryCreator sessionFactoryCreator) {
        return sessionFactoryCreator.create();
    }

    @Bean
    public HibernateSessionManager sessionManager(SessionFactory sessionFactory) {
        return new HibernateSessionManager(sessionFactory);
    }

    @Bean
    public HwCache<Long, User> myCache() {
        return new MyCache<>();
    }

    @Bean
    public HibernateUserDao userDao(HibernateSessionManager sessionManager) {
        return new HibernateUserDao(sessionManager);
    }

    @Bean
    public UserDBService userDaoDBService(UserDao userDao, HwCache<Long, User> cache) {
        UserDaoDBService userService = new UserDaoDBService(userDao);
        return new CachedUserDaoDBService(userService, cache);
    }

}
