package ru.otus;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.Address;
import ru.otus.core.model.Phone;
import ru.otus.core.model.User;
import ru.otus.core.service.UserDaoDBService;
import ru.otus.hibernate.DefaultSessionFactoryCreator;
import ru.otus.hibernate.HibernateFlywayMigrationManager;
import ru.otus.hibernate.HibernateSessionManager;
import ru.otus.hibernate.HibernateUserDao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class HomeWorkHibernate {

    private static final Logger logger = LoggerFactory.getLogger(HomeWorkHibernate.class);

    private static final String PATH_CONFIG_HIBER = "hibernate.cfg.xml";

    public static void main(String[] args) {
// настройка hibernate-а
        Configuration config = new Configuration().configure(PATH_CONFIG_HIBER);
        HibernateFlywayMigrationManager migrationManager = new HibernateFlywayMigrationManager(config);
        migrationManager.migrate();

        DefaultSessionFactoryCreator sessionFactoryCreator = new DefaultSessionFactoryCreator(config);
        sessionFactoryCreator.addEntityClasses(User.class, Phone.class, Address.class);
        SessionFactory sessionFactory = sessionFactoryCreator.create();
        HibernateSessionManager sessionManager = new HibernateSessionManager(sessionFactory);


// Работа с пользователем
        UserDao userDao = new HibernateUserDao(sessionManager);

// Код дальше должен остаться, т.е. userDao должен использоваться
        var dbServiceUser = new UserDaoDBService(userDao);
        User newUser = new User(null, "new user", 17);
        Address newAddress = new Address(newUser, "new street");
        List<Phone> newPhones = Arrays.asList(
                new Phone(newUser, "111"),
                new Phone(newUser, "222")
        );
        newUser.setAddress(newAddress);
        newUser.addPhones(newPhones);

        var idUser = dbServiceUser.save(newUser);
        Optional<User> user = dbServiceUser.getModel(idUser);

        user.ifPresentOrElse(
                crUser -> {
                    logger.info("created user, name:{}", crUser.getName());
                    logger.info("created address, street: {}", crUser.getAddress().getStreet());
                    crUser.getPhones().forEach(p ->
                            logger.info("created phone, number: {}", p.getNumber()));
                },
                () -> logger.info("user was not created")
        );
    }

}
