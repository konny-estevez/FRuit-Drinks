package com.ppm.businessLogic;

import com.ppm.entities.IngredientName;
import com.ppm.entities.InventoryIngredient;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<InventoryIngredient> inventoryIngredients;

    public Inventory() {
        inventoryIngredients = new ArrayList<>();
    }

    public List<InventoryIngredient> getIngredients() {
        return inventoryIngredients;
    }

    public void addIngredient(InventoryIngredient inventoryIngredient) {
        this.inventoryIngredients.add(inventoryIngredient);
    }

    public InventoryIngredient getIngredient(IngredientName ingredientName) {
        return inventoryIngredients.stream()
                .filter(item -> item.getName().equals(ingredientName))
                .findAny().orElse(null);
    }
}
