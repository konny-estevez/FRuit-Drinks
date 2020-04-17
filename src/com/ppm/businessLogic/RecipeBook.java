package com.ppm.businessLogic;

import com.ppm.entities.*;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RecipeBook {
    private List<Recipe> recipes;

    public RecipeBook() {
        recipes = new ArrayList<>();
    }

    public void addFruitDrinkRecipe(DrinkName drinkName, int quantity, MeasureUnit unit, float smallSizePrice, float mediumSizePrice, float largeSizePrice,
                                    RecipeIngredient fruitIngredient, @Nullable RecipeIngredient fruitIngredient2, @Nullable RecipeIngredient fruitIngredient3) {
        Recipe recipe = new Recipe(drinkName, quantity, unit, smallSizePrice, mediumSizePrice, largeSizePrice);
        recipe.addIngredient(fruitIngredient);
        if (fruitIngredient2 != null)
            recipe.addIngredient(fruitIngredient2);
        if (fruitIngredient3 != null)
            recipe.addIngredient(fruitIngredient3);
        recipe.addIngredient(new RecipeIngredient(IngredientName.Ice, 30, MeasureUnit.Mililiter));
        recipe.addIngredient(new RecipeIngredient(IngredientName.CondensedMilk, 20, MeasureUnit.Mililiter));
        recipe.addIngredient(new RecipeIngredient(IngredientName.Sugar, 8, MeasureUnit.Gram));
        recipes.add(recipe);
    }

    public Recipe getRecipe(DrinkName drink) {
        Recipe recipe = recipes.stream()
                .filter(item -> item.getName().equals(drink))
                .findAny().orElse(null);
        return recipe;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
