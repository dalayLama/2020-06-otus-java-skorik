package ru.otus.jdbc.mapper;

public interface FactoryEntityMetaData {

    <T> EntityClassMetaData<T> create(Class<T> tClass);

}
