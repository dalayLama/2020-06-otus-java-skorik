package ru.otus.jdbc.mapper.impls;

import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.jdbc.mapper.EntitySQLMetaData;
import ru.otus.jdbc.mapper.JdbcMapper;
import ru.otus.jdbc.mapper.exceptions.*;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;
import ru.otus.utils.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcMapperImpl<T> implements JdbcMapper<T> {

    private final SessionManagerJdbc sessionManager;

    private final DbExecutor executor;

    private final EntitySQLMetaData sqlMetaData;

    private final EntityClassMetaData<T> metaData;

    public JdbcMapperImpl(
            SessionManagerJdbc sessionManager,
            DbExecutor executor,
            EntitySQLMetaData sqlMetaData,
            EntityClassMetaData<T> metaData) {
        this.sessionManager = sessionManager;
        this.executor = executor;
        this.sqlMetaData = sqlMetaData;
        this.metaData = metaData;
    }

    @Override
    public void insert(T objectData) throws JdbcMapperException {
        try {
            if (!isIdNull(objectData)) {
                throw new NotNullIdException(objectData);
            }
            String insertSql = sqlMetaData.getInsertSql();
            List<Object> values = getInsertedFieldsValues(objectData);
            Object newId = executor.executeInsert(getConnection(), insertSql, values, this::extractId);
            setNewId(newId, objectData);
        } catch (SQLException e) {
            throw new InsertException(e);
        }
    }

    @Override
    public void update(T objectData) {
        try {
            if (isIdNull(objectData)) {
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
        if (isIdNull(objectData)) {
            insert(objectData);
        } else {
            update(objectData);
        }
    }

    @Override
    public T findById(Object id, Class<T> clazz) {
        try {
            String selectByIdSql = sqlMetaData.getSelectByIdSql();
            return executor
                    .executeSelectById(getConnection(), selectByIdSql, id, this::createObject)
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

    private boolean isIdNull(T object) {
        return ReflectionUtils.isNullField(object, metaData.getIdField());
    }

    private void setNewId(Object newId, T objectData) {
        try {
            ReflectionUtils.injectValue(objectData, metaData.getIdField(), newId);
        } catch (Exception e) {
            throw new InjectNewValueIdException();
        }
    }

    private Object extractId(ResultSet resultSet) throws JdbcMapperException {
        try {
            return resultSet.getObject(metaData.getIdField().getName(), metaData.getIdField().getType());
        } catch (Exception e) {
            throw new JdbcMapperException(e);
        }
    }

    private T createObject(ResultSet resultSet) throws JdbcMapperException {
        try {
            T newObject = metaData.getConstructor().newInstance();
            fillObject(resultSet, newObject);
            return newObject;
        } catch (Exception e) {
            throw new JdbcMapperException(e);
        }
    }

    private void fillObject(ResultSet resultSet, T object) throws JdbcMapperException {
        for (Field f : metaData.getAllFields()) {
            try {
                Object fieldValue = resultSet.getObject(f.getName());
                ReflectionUtils.injectValue(object, f, fieldValue);
            } catch (Exception e) {
                throw new JdbcMapperException(e);
            }
        }
    }

}
