package ru.otus.jdbc.mapper.impls;

import ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.jdbc.mapper.EntitySQLMetaData;
import ru.otus.jdbc.mapper.SqlMetaDataFactory;

public class SqlMetaDataFactoryImpl implements SqlMetaDataFactory {
    @Override
    public EntitySQLMetaData create(EntityClassMetaData<?> metaData) {
        return new EntitySQLMetaDataByClassMeta(metaData);
    }
}
