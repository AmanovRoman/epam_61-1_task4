package com.epam.spring.hometask.domain.enums;

public enum EventRating {
    LOW(1),
    MID(2),
    HIGH(3);

    private int value;

    EventRating(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}