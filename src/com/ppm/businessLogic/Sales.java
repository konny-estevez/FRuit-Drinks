package com.ppm.businessLogic;

import com.ppm.entities.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Sales {
    private List<Sale> sales;
    private List<SaleSummary> salesSummary;

    public Sales() {
        sales = new ArrayList<>();
        salesSummary = new ArrayList<>();
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void addSale(DrinkName drinkName, DrinkSize drinkSize, float retailPrice, float drinkCost) {
        sales.add(new Sale(LocalDate.now(), new Drink(drinkName, drinkSize, retailPrice, drinkCost)));
    }

    public List<SaleSummary> getSalesSummary() {
        LocalDate minDate = sales.stream().map(item -> item.getDate()).min(Comparator.naturalOrder()).get();
        LocalDate maxDate = sales.stream().map(item -> item.getDate()).max(Comparator.naturalOrder()).get();
        salesSummary.clear();
        do {
            salesSummary.add(new SaleSummary(minDate, sales.stream().filter(item -> item.getDate().equals(minDate)).map(item -> item.getDrink()).collect(Collectors.toList()),
                    sales.stream().filter(item -> item.getDate().equals(minDate)).map(item -> item.getDrink().getRetailPrice()).reduce(0f, Float::sum),
                    sales.stream().filter(item -> item.getDate().equals(minDate)).map(item -> item.getDrink().getCost()).reduce(0f, Float::sum)));
            minDate.plusDays(1);
        } while (minDate.isBefore(maxDate));

        return salesSummary;
    }
}
