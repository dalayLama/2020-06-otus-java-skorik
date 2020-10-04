package ru.otus.jdbc.mapper.impls;

import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.mapper.*;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

public class DefaultJdbcMapperFactory implements JdbcMapperFactory {

    private final SessionManagerJdbc sessionManager;

    private final EntityMetaDataFactory entityMetaDataFactory;

    private final SqlMetaDataFactory sqlMetaDataFactory;

    public DefaultJdbcMapperFactory(SessionManagerJdbc sessionManager,
                                    EntityMetaDataFactory entityMetaDataFactory,
                                    SqlMetaDataFactory sqlMetaDataFactory) {
        this.sessionManager = sessionManager;
        this.entityMetaDataFactory = entityMetaDataFactory;
        this.sqlMetaDataFactory = sqlMetaDataFactory;
    }

    @Override
    public <T> JdbcMapper<T> create(Class<T> tClass, DbExecutor<T> dbExecutor, Adapter<T> adapter) {
        EntityClassMetaData<T> meta = entityMetaDataFactory.create(tClass);
        EntitySQLMetaData sqlMetaData = sqlMetaDataFactory.create(meta);
        return new JdbcMapperImpl<>(sessionManager, dbExecutor, sqlMetaData, meta, adapter);
    }
}
