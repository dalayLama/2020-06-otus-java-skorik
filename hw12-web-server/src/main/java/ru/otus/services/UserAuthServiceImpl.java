package ru.otus.services;

import ru.otus.core.service.UserDBService;

public class UserAuthServiceImpl implements UserAuthService {

    private final UserDBService userDao;

    public UserAuthServiceImpl(UserDBService userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return userDao.findByLogin(login)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

}
