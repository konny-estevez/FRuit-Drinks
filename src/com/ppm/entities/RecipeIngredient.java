package com.ppm.entities;

public class RecipeIngredient {
    private int quantity;
    private IngredientName ingredientName;
    private MeasureUnit measureUnit;

    public RecipeIngredient(IngredientName ingredientName, int quantity, MeasureUnit measureUnit)
    {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.measureUnit = measureUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public IngredientName getName() {
        return ingredientName;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }
}
