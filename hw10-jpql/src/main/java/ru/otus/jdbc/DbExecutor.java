package ru.otus.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface DbExecutor<T> {

    <ID> ID executeInsert(Connection connection, String sql, List<Object> params, Function<ResultSet, ID> rsHandler) throws SQLException;

    void executeUpdate(Connection connection, String sql, List<Object> params) throws SQLException;

    Optional<T> executeSelectById(Connection connection, String sql, Object id, Function<ResultSet, T> rsHandler) throws SQLException;

    Collection<? extends T> executeSelect(Connection connection, String sql, List<Object> params,
                                          Function<ResultSet, T> rsHandler) throws SQLException;
}
