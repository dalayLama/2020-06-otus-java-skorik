package ru.otus.core.service;

import ru.otus.core.model.User;

import java.util.Optional;

public interface UserDBService extends DBService<User, Long> {

    Optional<User> findByLogin(String login);

}
