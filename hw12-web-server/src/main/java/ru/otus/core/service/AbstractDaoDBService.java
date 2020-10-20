package ru.otus.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.Dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDaoDBService<T, ID> implements DBService<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoDBService.class);

    private final Dao<T, ID> dao;

    protected AbstractDaoDBService(Dao<T, ID> dao) {
        this.dao = dao;
    }

    @Override
    public ID save(T model) {
        try (var sessionManager = dao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                var modelId = dao.insertEntity(model);
                sessionManager.commitSession();

                logger.info("created model: {}", modelId);
                return modelId;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<T> getModel(ID id) {
        try (var sessionManager = dao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<T> modelOptional = dao.findById(id);

                logger.info("model: {}", modelOptional.orElse(null));
                return modelOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

    @Override
    public List<? extends T> getAll() {
        try (var sm = dao.getSessionManager()) {
            sm.beginSession();
            try {
                return dao.findAll();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return Collections.emptyList();
            }
        }
    }

    protected Dao<T, ID> getDao() {
        return dao;
    }

}
