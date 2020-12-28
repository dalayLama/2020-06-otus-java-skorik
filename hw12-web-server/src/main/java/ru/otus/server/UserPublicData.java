package ru.otus.server;

import ru.otus.core.model.User;

public class UserPublicData {

    private final Long id;

    private final String name;

    private final String login;

    private final int age;

    public UserPublicData(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.login = user.getLogin();
        this.age = user.getAge();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public int getAge() {
        return age;
    }
}
