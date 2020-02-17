package com.epam.spring.hometask.domain;

public abstract class DomainId {
    private int id;

    public DomainId() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
