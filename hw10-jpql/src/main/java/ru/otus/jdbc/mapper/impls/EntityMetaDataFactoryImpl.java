package ru.otus.jdbc.mapper.impls;

import ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.jdbc.mapper.EntityMetaDataFactory;

public class EntityMetaDataFactoryImpl implements EntityMetaDataFactory {
    @Override
    public <T> EntityClassMetaData<T> create(Class<T> tClass) {
        return new EntityClassMetaDataByReflection<>(tClass);
    }
}
