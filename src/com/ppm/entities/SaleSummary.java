package com.ppm.entities;

import java.time.LocalDate;
import java.util.List;

public class SaleSummary {
    private LocalDate date;
    private List<Drink> drinks;
    private float totalPrice, totalCost;

    public SaleSummary(LocalDate date, List<Drink> drinks, float totalPrice, float totalCost) {
        this.date = date;
        this.drinks = drinks;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public int getTotalDrinks() {
        return drinks.size();
    }
}
