package ru.otus.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;

import java.util.Optional;

public class UserDaoDBService extends AbstractDaoDBService<User, Long> implements UserDBService {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoDBService.class);

    public UserDaoDBService(UserDao dao) {
        super(dao);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try (var sm = getDao().getSessionManager()) {
            sm.beginSession();
            return getDao().findByLogin(login);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    protected UserDao getDao() {
        return (UserDao) super.getDao();
    }
}