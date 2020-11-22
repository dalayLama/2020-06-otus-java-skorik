package ru.otus.core.model;

import java.io.Serializable;

public interface HibernateModel<ID extends Serializable> {

    ID getId();

}
