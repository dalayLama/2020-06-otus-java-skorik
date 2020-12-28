package ru.otus.core.service;

import ru.otus.cachehw.HwCache;
import ru.otus.core.model.User;

public class CachedUserDaoDBService extends CachedAbstractDaoDBService<User, Long> {

    public CachedUserDaoDBService(DBService<User, Long> userService, HwCache<Long, User> cache) {
        super(userService, cache);
    }

}
