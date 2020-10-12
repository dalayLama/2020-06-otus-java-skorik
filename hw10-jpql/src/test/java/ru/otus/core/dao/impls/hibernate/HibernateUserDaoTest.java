package ru.otus.core.dao.impls.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.core.model.User;
import ru.otus.hibernate.DefaultSessionFactoryCreator;
import ru.otus.hibernate.HibernateFlywayMigrationManager;
import ru.otus.hibernate.HibernateSessionManager;
import ru.otus.hibernate.HibernateUserDao;
import ru.otus.hibernate.exceptions.NotNullIdException;
import ru.otus.hibernate.exceptions.NullIdException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HibernateUserDaoTest {

    private static final String HIBERNATE_CONFIG_FILE = "hibernate.cfg.xml";

    private SessionFactory sessionFactory;

    private HibernateSessionManager hibernateSessionManager;

    private HibernateUserDao hibernateUserDao;

    @BeforeEach
    public void setUp() {
        var configuration = new Configuration().configure(HIBERNATE_CONFIG_FILE);

        HibernateFlywayMigrationManager migrationManager = new HibernateFlywayMigrationManager(configuration);
        migrationManager.migrate();

        var sessionFactoryCreator = new DefaultSessionFactoryCreator(configuration);
        sessionFactoryCreator.addEntityClasses(User.class);

        sessionFactory = sessionFactoryCreator.create();
        hibernateSessionManager = new HibernateSessionManager(sessionFactory);
        hibernateUserDao = new HibernateUserDao(hibernateSessionManager);
    }

    @Test
    public void shouldFindById() {
        final long expectedId = 1L;
        final String expectedName = "Artur";
        final int expectedAge = 27;

        try (var manager = hibernateUserDao.getSessionManager()) {
            manager.beginSession();
            Optional<User> byId = hibernateUserDao.findById(expectedId);
            assertThat(byId.isPresent()).isTrue();
            assertThat(byId.get().getId()).isEqualTo(expectedId);
            assertThat(byId.get().getName()).isEqualTo(expectedName);
            assertThat(byId.get().getAge()).isEqualTo(expectedAge);
        }
    }

    @Test
    public void shouldInsertNewUser() {
        long expectedNewId = 2L;
        var newUser = new User(null, "Victor", 87);

        Long newId;
        try (var manager = hibernateUserDao.getSessionManager()) {
            manager.beginSession();
            newId = hibernateUserDao.insertEntity(newUser);
        }
        assertThat(newId).isEqualTo(expectedNewId);

        try (var manager = hibernateUserDao.getSessionManager()) {
            manager.beginSession();
            Optional<User> byId = hibernateUserDao.findById(newId);
            assertThat(byId.isPresent()).isTrue();
            assertThat(byId.get().getId()).isEqualTo(newId);
            assertThat(byId.get().getName()).isEqualTo(newUser.getName());
            assertThat(byId.get().getAge()).isEqualTo(newUser.getAge());
        }
    }

    @Test
    public void shouldUpdateUser() {
        final long id = 1L;
        var newValues = new User(id, "Victor", 87);
        try (var manager = hibernateUserDao.getSessionManager()) {
            manager.beginSession();
            hibernateUserDao.updateEntity(newValues);
        }

        try (var manager = hibernateUserDao.getSessionManager()) {
            manager.beginSession();
            Optional<User> byId = hibernateUserDao.findById(id);
            assertThat(byId.isPresent()).isTrue();
            assertThat(byId.get().getId()).isEqualTo(id);
            assertThat(byId.get().getName()).isEqualTo(newValues.getName());
            assertThat(byId.get().getAge()).isEqualTo(newValues.getAge());
        }
    }

    @Test
    public void shouldThrowExceptionWhenInsertWithNotNullId() {
        var newUser = new User(1L, "Victor", 87);
        assertThrows(NotNullIdException.class, () -> hibernateUserDao.insertEntity(newUser));
    }

    @Test
    public void shouldThrowExceptionWhenUpdateWithNullId() {
        var newUser = new User(null, "Victor", 87);
        assertThrows(NullIdException.class, () -> hibernateUserDao.updateEntity(newUser));
    }

}