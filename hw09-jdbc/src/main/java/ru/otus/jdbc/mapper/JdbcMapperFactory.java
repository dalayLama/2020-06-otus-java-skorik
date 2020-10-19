package ru.otus.jdbc.mapper;

import ru.otus.jdbc.DbExecutor;

public interface JdbcMapperFactory {

    <T> JdbcMapper<T> create(Class<T> tClass, DbExecutor dbExecutor);

}
