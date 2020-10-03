package ru.otus.jdbc.mapper.impls;

import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.mapper.*;
import ru.otus.jdbc.mapper.exceptions.*;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcMapperImpl<T> implements JdbcMapper<T> {

    private final SessionManagerJdbc sessionManager;

    private final DbExecutor<T> executor;

    private final EntitySQLMetaData sqlMetaData;

    private final EntityClassMetaData<T> metaData;

    private final IdNullValueChecker<T> idNullValueChecker;

    private final Adapter<T> adapter;

    public JdbcMapperImpl(
            SessionManagerJdbc sessionManager,
            DbExecutor<T> executor,
            EntitySQLMetaData sqlMetaData,
            EntityClassMetaData<T> metaData,
            IdNullValueChecker<T> idNullValueChecker,
            Adapter<T> adapter) {
        this.sessionManager = sessionManager;
        this.executor = executor;
        this.sqlMetaData = sqlMetaData;
        this.metaData = metaData;
        this.idNullValueChecker = idNullValueChecker;
        this.adapter = adapter;
    }

    public JdbcMapperImpl(
            SessionManagerJdbc sessionManager, DbExecutor<T> executor,
            EntitySQLMetaData sqlMetaData,
            EntityClassMetaData<T> metaData,
            Adapter<T> adapter) {
        this(sessionManager, executor, sqlMetaData,
                metaData, new DefaultIdNullValueChecker(metaData.getIdField()), adapter);
    }

    @Override
    public void insert(T objectData) throws JdbcMapperException {
        try {
            if (!idNullValueChecker.check(objectData)) {
                throw new NotNullIdException(objectData);
            }
            String insertSql = sqlMetaData.getInsertSql();
            List<Object> values = getInsertedFieldsValues(objectData);
            Object newId = executor.executeInsert(getConnection(), insertSql, values, resultSet -> {
                        try {
                            return adapter.extractId(resultSet);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
            setNewId(newId, objectData);
        } catch (SQLException e) {
            throw new InsertException(e);
        }
    }

    private void setNewId(Object newId, T objectData) {
        try {
            metaData.getIdField().setAccessible(true);
            metaData.getIdField().set(objectData, newId);
        } catch (Exception e) {
            throw new InjectNewValueIdException();
        }
    }

    @Override
    public void update(T objectData) {
        try {
            if (idNullValueChecker.check(objectData)) {
                throw new NullIdException(objectData);
            }
            String updateSql = sqlMetaData.getUpdateSql();
            executor.executeUpdate(getConnection(), updateSql, getUpdateFieldsValues(objectData));
        } catch (SQLException e) {
            throw new UpdateException(e);
        }
    }

    @Override
    public void insertOrUpdate(T objectData) {
        if (idNullValueChecker.check(objectData)) {
            insert(objectData);
        } else {
            update(objectData);
        }
    }

    @Override
    public T findById(Object id, Class<T> clazz) {
        try {
            String selectByIdSql = sqlMetaData.getSelectByIdSql();
            return executor.executeSelectById(getConnection(), selectByIdSql, id, resultSet -> {
                        try {
                            return adapter.convert(resultSet);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElse(null);
        } catch (SQLException e) {
            throw new SelectException(e);
        }
    }

    @Override
    public SessionManagerJdbc getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }

    private List<Object> getInsertedFieldsValues(T objectData) throws ReadObjectFieldsValues {
        List<Field> fieldsWithoutId = metaData.getFieldsWithoutId();
        List<Object> result = new ArrayList<>(fieldsWithoutId.size());
        try {
            for (Field f : fieldsWithoutId) {
                f.setAccessible(true);
                result.add(f.get(objectData));
            }
            return result;
        } catch (Exception e) {
            throw new ReadObjectFieldsValues(e, objectData);
        }
    }

    private List<Object> getUpdateFieldsValues(T objectData) throws ReadObjectFieldsValues {
        List<Object> params = getInsertedFieldsValues(objectData);
        try {
            Object idValue = metaData.getIdField().get(objectData);
            params.add(idValue); // добавляем id как последний параметр для запроса
            return params;
        } catch (Exception e) {
            throw new ReadObjectFieldsValues(e, objectData);
        }
    }

}
