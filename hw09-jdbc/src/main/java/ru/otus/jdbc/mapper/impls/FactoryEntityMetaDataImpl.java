package ru.otus.jdbc.mapper.impls;

import ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.jdbc.mapper.FactoryEntityMetaData;

public class FactoryEntityMetaDataImpl implements FactoryEntityMetaData {
    @Override
    public <T> EntityClassMetaData<T> create(Class<T> tClass) {
        return new EntityClassMetaDataByReflection<>(tClass);
    }
}
