package ru.otus.core.service;

import ru.otus.cachehw.HwCache;

import java.util.Optional;

public abstract class CachedAbstractDaoDBService<T, ID> implements DBService<T, ID> {

    private final DBService<T, ID> service;

    private final HwCache<ID, T> cache;

    public CachedAbstractDaoDBService(DBService<T, ID> service, HwCache<ID, T> cache) {
        this.service = service;
        this.cache = cache;
    }

    @Override
    public Optional<T> getModel(ID id) {
        T t = cache.get(id);
        if (t != null) {
            return Optional.of(t);
        }

        Optional<T> model = service.getModel(id);
        model.ifPresent(m -> cache.put(id, m));
        return model;
    }

    @Override
    public ID save(T model) {
        ID id = service.save(model);
        cache.put(id, model);
        return id;
    }
}
