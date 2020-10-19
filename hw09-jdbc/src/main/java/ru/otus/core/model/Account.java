package ru.otus.core.model;

import ru.otus.core.annotations.Id;

public class Account {

    @Id
    private Long no;

    private String type;

    private double rest;

    public Account() {
    }

    public Account(Long no, String type, double rest) {
        this.no = no;
        this.type = type;
        this.rest = rest;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRest() {
        return rest;
    }

    public void setRest(double rest) {
        this.rest = rest;
    }
}
