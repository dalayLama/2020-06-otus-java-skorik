package ru.otus.jdbc.mapper;

public interface SqlMetaDataFactory {

    EntitySQLMetaData create(EntityClassMetaData<?> metaData);

}
