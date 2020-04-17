package com.ppm.entities;

import java.time.LocalDate;

public class Sale {
    private LocalDate date;
    private Drink drink;

    public Sale(LocalDate date, Drink drink) {
        this.date = date;
        this.drink = drink;
    }

    public LocalDate getDate() {
        return date;
    }

    public Drink getDrink() {
        return drink;
    }
}
