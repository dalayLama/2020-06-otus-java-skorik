package ru.otus.jdbc.mapper;

public interface FactorySqlMetaData {

    EntitySQLMetaData create(EntityClassMetaData<?> metaData);

}
