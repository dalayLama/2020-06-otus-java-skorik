package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.security.LoginService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.otus.cachehw.HwCache;
import ru.otus.cachehw.MyCache;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.Address;
import ru.otus.core.model.Phone;
import ru.otus.core.model.User;
import ru.otus.core.service.CachedUserDaoDBService;
import ru.otus.core.service.UserDaoDBService;
import ru.otus.hibernate.DefaultSessionFactoryCreator;
import ru.otus.hibernate.HibernateFlywayMigrationManager;
import ru.otus.hibernate.HibernateSessionManager;
import ru.otus.hibernate.HibernateUserDao;
import ru.otus.server.UsersWebServer;
import ru.otus.server.UsersWebServerWithBasicSecurity;
import ru.otus.services.TemplateProcessor;
import ru.otus.services.TemplateProcessorImpl;
import ru.otus.services.UserDBLoginService;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/users

    // REST сервис
    http://localhost:8080/api/user/3
*/
public class WebServerWithBasicSecurityDemo {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";
    private static final String REALM_NAME = "AnyRealm";
    private static final String PATH_CONFIG_HIBER = "hibernate.cfg.xml";

    public static void main(String[] args) throws Exception {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        Configuration config = new Configuration().configure(PATH_CONFIG_HIBER);
        HibernateFlywayMigrationManager migrationManager = new HibernateFlywayMigrationManager(config);
        migrationManager.migrate();

        DefaultSessionFactoryCreator sessionFactoryCreator = new DefaultSessionFactoryCreator(config);
        sessionFactoryCreator.addEntityClasses(User.class, Phone.class, Address.class);
        SessionFactory sessionFactory = sessionFactoryCreator.create();
        HibernateSessionManager sessionManager = new HibernateSessionManager(sessionFactory);


        HwCache<Long, User> cache = new MyCache<>();
        UserDao userDao = new HibernateUserDao(sessionManager);
        UserDaoDBService userService = new UserDaoDBService(userDao);
        CachedUserDaoDBService cachedUserService = new CachedUserDaoDBService(userService, cache);

        LoginService loginService = new UserDBLoginService(cachedUserService);

        UsersWebServer usersWebServer = new UsersWebServerWithBasicSecurity(WEB_SERVER_PORT,
                loginService, cachedUserService, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }

}
