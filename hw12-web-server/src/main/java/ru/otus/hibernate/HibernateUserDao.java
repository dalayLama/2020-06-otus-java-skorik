package ru.otus.hibernate;

import org.hibernate.query.Query;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;

import java.util.Optional;

public class HibernateUserDao extends AbstractHibernateDao<User, Long> implements UserDao {

    public HibernateUserDao(HibernateSessionManager hibernateSessionManager) {
        super(hibernateSessionManager);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Query<User> query = getCurrentSession().createQuery("select u from User u where u.login = :login", User.class);
        query.setParameter("login", login);
        User byLogin = query.getSingleResult();
        return Optional.ofNullable(byLogin);
    }

    @Override
    protected Class<User> getClassEntity() {
        return User.class;
    }

}
