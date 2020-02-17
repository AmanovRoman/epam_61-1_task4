package com.epam.spring.hometask.domain.enums;

public enum UserType {
    ADMIN(1),
    VISITOR(2);

    private int type;

    UserType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}