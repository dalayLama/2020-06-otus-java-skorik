package ru.otus.core.dao;

import ru.otus.core.model.User;

import java.util.Optional;

public interface UserDao extends Dao<User, Long> {

    Optional<User> findByLogin(String login);

}
