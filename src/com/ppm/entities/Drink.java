package com.ppm.entities;

public class Drink {
    private DrinkName drinkName;
    private DrinkSize drinkSize;
    private float retailPrice, drinkCost;

    public Drink(DrinkName drinkName, DrinkSize drinkSize, float retailPrice, float drinkCost) {
        this.drinkName = drinkName;
        this.drinkSize = drinkSize;
        this.retailPrice = retailPrice;
        this.drinkCost = drinkCost;
    }

    public DrinkName getName() {
        return drinkName;
    }

    public DrinkSize getSize() {
        return drinkSize;
    }

    public float getRetailPrice() {
        return retailPrice;
    }

    public float getCost() {
        return drinkCost;
    }
}
