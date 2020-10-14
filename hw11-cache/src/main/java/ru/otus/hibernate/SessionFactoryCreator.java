package ru.otus.hibernate;

import org.hibernate.SessionFactory;

public interface SessionFactoryCreator {

    SessionFactory create();

}
