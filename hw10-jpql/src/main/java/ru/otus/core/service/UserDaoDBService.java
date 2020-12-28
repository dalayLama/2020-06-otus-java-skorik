package ru.otus.core.service;

import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;

public class UserDaoDBService extends AbstractDaoDBService<User, Long> implements UserDBService {

    public UserDaoDBService(UserDao dao) {
        super(dao);
    }

}