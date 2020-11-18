package ru.otus.core.service;

import java.util.Optional;

public interface DBService<T, ID> {

    ID save(T model);

    Optional<T> getModel(ID id);
}
