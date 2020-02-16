package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.User;

public abstract class AbstractStrategy {
    private double discountValue;
    private User user;
    private String strategyName;

    AbstractStrategy(double discountValue, String strategyName) {
        this.discountValue = discountValue;
        this.strategyName = strategyName;
    }

    double getDiscountValue() {
        return this.discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStrategyName() {
        return this.strategyName;
    }
}