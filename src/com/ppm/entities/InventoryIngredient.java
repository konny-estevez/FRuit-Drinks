package com.ppm.entities;

public class InventoryIngredient {
    private int quantity, minimumQuantity;
    private Ingredient ingredient;
    private float unitCost;

    public InventoryIngredient(IngredientName name, int quantity, MeasureUnit measureUnit, int minimumQuantity, float unitCost) {
        this.quantity = quantity;
        this.minimumQuantity = minimumQuantity;
        this.ingredient = new Ingredient(name, measureUnit);
        this.unitCost = unitCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public IngredientName getName() {
        return ingredient.getName();
    }

    public MeasureUnit getMeasureUnit() {
        return ingredient.getMeasureUnit();
    }

    public float getUnitCost() {
        return unitCost;
    }
}
