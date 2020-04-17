package com.ppm.entities;

public enum DrinkSize {
    Small(200),
    Medium(300),
    Large(500);

    final int quantity;

    DrinkSize(int quantity) {
        this.quantity = quantity;
    }

    public  int getValue() {
        return this.quantity;
    }
}

