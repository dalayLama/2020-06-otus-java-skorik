package ru.otus.server;

import java.util.Arrays;

public enum Roles {

    USER, ADMIN;

    static String[] allNames() {
        return Arrays.stream(values())
                .map(Enum::name)
                .toArray(String[]::new);
    }

}
