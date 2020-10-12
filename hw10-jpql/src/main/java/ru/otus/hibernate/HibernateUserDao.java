package ru.otus.hibernate;

import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;

public class HibernateUserDao extends AbstractHibernateDao<User, Long> implements UserDao {

    public HibernateUserDao(HibernateSessionManager hibernateSessionManager) {
        super(hibernateSessionManager);
    }

    @Override
    protected Class<User> getClassEntity() {
        return User.class;
    }
}
