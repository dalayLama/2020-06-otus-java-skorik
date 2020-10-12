package ru.otus.core.dao.impls.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.core.model.Address;
import ru.otus.core.model.Phone;
import ru.otus.core.model.User;
import ru.otus.hibernate.DefaultSessionFactoryCreator;
import ru.otus.hibernate.HibernateFlywayMigrationManager;
import ru.otus.hibernate.HibernateSessionManager;
import ru.otus.hibernate.HibernateUserDao;
import ru.otus.hibernate.exceptions.NotNullIdException;
import ru.otus.hibernate.exceptions.NullIdException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        sessionFactoryCreator.addEntityClasses(User.class, Address.class, Phone.class);

        sessionFactory = sessionFactoryCreator.create();
        hibernateSessionManager = new HibernateSessionManager(sessionFactory);
        hibernateUserDao = new HibernateUserDao(hibernateSessionManager);
    }

    @Test
    public void shouldFindById() {
        final long expectedId = 1L;
        final String expectedName = "Артур";
        final int expectedAge = 27;
        final String expectedAddress = "Арсеньева";
        final List<String> expectedPhones = Arrays.asList(
                "8888888", "9999999"
        );

        try (var manager = hibernateUserDao.getSessionManager()) {
            manager.beginSession();
            Optional<User> byId = hibernateUserDao.findById(expectedId);
            assertThat(byId.isPresent()).isTrue();
            assertThat(byId.get().getId()).isEqualTo(expectedId);
            assertThat(byId.get().getName()).isEqualTo(expectedName);
            assertThat(byId.get().getAge()).isEqualTo(expectedAge);

            assertThat(byId.get().getAddress().getStreet()).isEqualTo(expectedAddress);
            List<String> phones = byId.get().getPhones().stream()
                    .map(Phone::getNumber)
                    .collect(Collectors.toList());
            assertThat(phones).containsAll(expectedPhones);
        }
    }

    @Test
    public void shouldInsertNewUser() {
        long expectedNewId = 2L;
        var newUser = new User(null, "Виктор", 87);
        var newPhone = new Phone(newUser, "8787");
        var newAddress = new Address( newUser, "new street");
        newUser.addPhones(Collections.singleton(newPhone));
        newUser.setAddress(newAddress);

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

            Address addedAddress = byId.get().getAddress();
            assertThat(addedAddress.getId()).isEqualTo(byId.get().getId());
            assertThat(addedAddress.getUser()).isSameAs(byId.get());
            assertThat(addedAddress.getStreet()).isEqualTo(newAddress.getStreet());

            Phone addedPhone = byId.get().getPhones().get(0);
            assertThat(addedPhone.getId()).isNotNull();
            assertThat(addedPhone.getUser()).isSameAs(byId.get());
            assertThat(addedPhone.getNumber()).isEqualTo(newPhone.getNumber());
        }
    }

    @Test
    public void shouldUpdateUser() {
        final long id = 1L;
        var newValuesUser = new User(id, "Виктор", 87);
        var newValuesAddress = new Address(newValuesUser, "Пирогова");
        var newValuesPhones = Arrays.asList(
                new Phone(1L, newValuesUser, "1111"),
                new Phone(2L, newValuesUser, "2222")
        );
        newValuesUser.setAddress(newValuesAddress);
        newValuesUser.addPhones(newValuesPhones);

        try (var manager = hibernateUserDao.getSessionManager()) {
            manager.beginSession();
            hibernateUserDao.updateEntity(newValuesUser);
        }

        try (var manager = hibernateUserDao.getSessionManager()) {
            manager.beginSession();
            Optional<User> byId = hibernateUserDao.findById(id);
            assertThat(byId.isPresent()).isTrue();
            assertThat(byId.get().getId()).isEqualTo(id);
            assertThat(byId.get().getName()).isEqualTo(newValuesUser.getName());
            assertThat(byId.get().getAge()).isEqualTo(newValuesUser.getAge());

            Address updatedAddress = byId.get().getAddress();
            assertThat(updatedAddress.getStreet()).isEqualTo(newValuesAddress.getStreet());

            List<Phone> updatedPhones = byId.get().getPhones();
            assertThat(updatedPhones.size()).isEqualTo(newValuesPhones.size());
            for (Phone phone : newValuesPhones) {
                Phone found = updatedPhones.stream()
                        .filter(up -> phone.getId().equals(up.getId()))
                        .findFirst().orElse(null);
                assertThat(found).isNotNull();
                assertThat(found.getNumber()).isEqualTo(phone.getNumber());
            }
        }
    }

    @Test
    public void shouldThrowExceptionWhenInsertWithNotNullId() {
        var newUser = new User(1L, "Виктор", 87);
        assertThrows(NotNullIdException.class, () -> hibernateUserDao.insertEntity(newUser));
    }

    @Test
    public void shouldThrowExceptionWhenUpdateWithNullId() {
        var newUser = new User(null, "Виктор", 87);
        assertThrows(NullIdException.class, () -> hibernateUserDao.updateEntity(newUser));
    }

}