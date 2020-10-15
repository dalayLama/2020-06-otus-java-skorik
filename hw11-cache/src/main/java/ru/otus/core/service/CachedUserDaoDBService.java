package ru.otus.core.service;

import ru.otus.cachehw.HwCache;
import ru.otus.core.dao.Dao;
import ru.otus.core.model.User;

public class CachedUserDaoDBService extends CachedAbstractDaoDBService<User, Long> {

    public CachedUserDaoDBService(Dao<User, Long> dao, HwCache<Long, User> cache) {
        super(dao, cache);
    }

}
