package ru.otus.core.dao;

import ru.otus.core.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {

    List<? extends T> findAll();

    Optional<T> findById(ID id);

    ID insertEntity(T entity);

    void updateEntity(T entity);

    void insertOrUpdate(T entity);

    SessionManager getSessionManager();
}
