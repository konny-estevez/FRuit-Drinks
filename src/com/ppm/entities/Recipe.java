package com.ppm.entities;

import com.ppm.businessLogic.Shop;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private DrinkName name;
    private List<RecipeIngredient> ingredients;
    private int quantity;
    private MeasureUnit measureUnit;
    private float unitCost, smallSizePrice, mediumSizePrice, largeSizePrice;

    public Recipe(DrinkName drink, int quantity, MeasureUnit unit, float smallSizePrice, float mediumSizePrice, float largeSizePrice) {
        this.name = drink;
        this.quantity = quantity;
        this.measureUnit = unit;
        this.smallSizePrice = smallSizePrice;
        this.mediumSizePrice = mediumSizePrice;
        this.largeSizePrice = largeSizePrice;
        this.unitCost = 0;
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(RecipeIngredient ingredient) {
        if (this.ingredients == null)
            this.ingredients = new ArrayList<>();
        this.ingredients.add(ingredient);
    }

    public int getQuantity() {
        return quantity;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public DrinkName getName() {
        return name;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return ingredients;
    }

    public float getUnitCost() {
        if (unitCost == 0) {
            for (RecipeIngredient recipeIngredient : ingredients) {
                unitCost += recipeIngredient.getQuantity() * Shop.getInventory().getIngredient(recipeIngredient.getName()).getUnitCost();
            }
            unitCost /= quantity;
        }
        return unitCost;
    }

    public float getRetailPrice(DrinkSize drinkSize) {
        switch (drinkSize) {
            case Small:
                return smallSizePrice;
            case Medium:
                return mediumSizePrice;
            case Large:
                return largeSizePrice;
            default:
                return -1;
        }
    }
}
