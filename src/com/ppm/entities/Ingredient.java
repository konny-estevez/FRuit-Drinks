package com.ppm.entities;

public class Ingredient {
    private IngredientName name;
    private MeasureUnit measureUnit;

    public Ingredient(IngredientName name, MeasureUnit measureUnit) {
        this.name = name;
        this.measureUnit = measureUnit;
    }

    public IngredientName getName() {
        return name;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }
}
