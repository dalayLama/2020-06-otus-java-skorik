package ru.otus.core.service;

import ru.otus.cachehw.HwCache;
import ru.otus.core.model.User;

import java.util.Optional;

public class CachedUserDaoDBService extends CachedAbstractDaoDBService<User, Long> implements UserDBService {

    public CachedUserDaoDBService(UserDBService userService, HwCache<Long, User> cache) {
        super(userService, cache);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return getService().findByLogin(login);
    }

    @Override
    protected UserDBService getService() {
        return (UserDBService) super.getService();
    }
}
