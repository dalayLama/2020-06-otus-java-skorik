package ru.otus.core.model;

import ru.otus.core.annotations.Id;

public class Account {

    @Id
    private final long no;

    private final String type;

    private final double rest;

    public Account(long no, String type, double rest) {
        this.no = no;
        this.type = type;
        this.rest = rest;
    }

    public long getNo() {
        return no;
    }

    public String getType() {
        return type;
    }

    public double getRest() {
        return rest;
    }
}
