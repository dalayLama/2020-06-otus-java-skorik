package ru.otus.jdbc.mapper;

public interface EntityMetaDataFactory {

    <T> EntityClassMetaData<T> create(Class<T> tClass);

}
