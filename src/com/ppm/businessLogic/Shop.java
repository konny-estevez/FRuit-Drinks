package com.ppm.businessLogic;

import com.ppm.entities.*;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class Shop {
    private static Inventory inventory;
    private static Sales salesReport;
    private static RecipeBook recipeBook;

    public static void loadRecipes() {
        recipeBook = new RecipeBook();
        recipeBook.addFruitDrinkRecipe(DrinkName.Strawberry, 100, MeasureUnit.Mililiter, 1.5f, 2, 3.5f,
                new RecipeIngredient(IngredientName.Strawberry, 50, MeasureUnit.Gram), null, null);
        recipeBook.addFruitDrinkRecipe(DrinkName.Banana, 100, MeasureUnit.Mililiter, 1.5f, 2, 3.5f,
                new RecipeIngredient(IngredientName.Banana, 60, MeasureUnit.Gram), null, null);
        recipeBook.addFruitDrinkRecipe(DrinkName.Mango, 100, MeasureUnit.Mililiter, 1.5f, 2, 3.5f,
                new RecipeIngredient(IngredientName.Mango, 70, MeasureUnit.Gram), null, null);
        recipeBook.addFruitDrinkRecipe(DrinkName.StrawberryBanana, 100, MeasureUnit.Mililiter, 2, 2.8f, 4,
                new RecipeIngredient(IngredientName.Strawberry, 25, MeasureUnit.Gram),
                new RecipeIngredient(IngredientName.Banana, 30, MeasureUnit.Gram), null);
        recipeBook.addFruitDrinkRecipe(DrinkName.StrawberryMango, 100, MeasureUnit.Mililiter, 2, 2.8f, 4,
                new RecipeIngredient(IngredientName.Strawberry, 25, MeasureUnit.Gram),
                new RecipeIngredient(IngredientName.Mango, 35, MeasureUnit.Gram), null);
        recipeBook.addFruitDrinkRecipe(DrinkName.BananaMango, 100, MeasureUnit.Mililiter, 2, 2.8f, 4,
                new RecipeIngredient(IngredientName.Banana, 30, MeasureUnit.Gram),
                new RecipeIngredient(IngredientName.Mango, 35, MeasureUnit.Gram), null);
        recipeBook.addFruitDrinkRecipe(DrinkName.SuperMix, 100, MeasureUnit.Mililiter, 2.5f, 3.5f, 5,
                new RecipeIngredient(IngredientName.Strawberry, 17, MeasureUnit.Gram),
                new RecipeIngredient(IngredientName.Banana, 20, MeasureUnit.Gram),
                new RecipeIngredient(IngredientName.Mango, 23, MeasureUnit.Gram));
    }

    public static void loadInventory(){
        final int warningFactor = 4;
        int warningQuantity, drinkSize = DrinkSize.Medium.getValue();
        List<Recipe> auxRecipes;

        inventory = new Inventory();
        warningQuantity = recipeBook.getRecipes().stream().map(item -> drinkSize / item.getQuantity() * warningFactor * item.getRecipeIngredients().stream()
                .filter(innerItem -> innerItem.getName().equals(IngredientName.Ice)).findAny().map(innerItem -> innerItem.getQuantity()).get()).max(Integer::compare).get();
        inventory.addIngredient(new InventoryIngredient(IngredientName.Ice, 3000, MeasureUnit.Mililiter, warningQuantity, 0.0002f));
        warningQuantity = recipeBook.getRecipes().stream().map(item -> drinkSize / item.getQuantity() * warningFactor * item.getRecipeIngredients().stream()
                .filter(innerItem -> innerItem.getName().equals(IngredientName.CondensedMilk)).findAny().map(innerItem -> innerItem.getQuantity()).get()).max(Integer::compare).get();
        inventory.addIngredient(new InventoryIngredient(IngredientName.CondensedMilk, 2000, MeasureUnit.Mililiter, warningQuantity, 0.00625f));
        warningQuantity = recipeBook.getRecipes().stream().map(item -> drinkSize / item.getQuantity() * warningFactor * item.getRecipeIngredients().stream()
                .filter(innerItem -> innerItem.getName().equals(IngredientName.Sugar)).findAny().map(innerItem -> innerItem.getQuantity()).get()).max(Integer::compare).get();
        inventory.addIngredient(new InventoryIngredient(IngredientName.Sugar, 800, MeasureUnit.Gram, warningQuantity, 0.001f));
        auxRecipes = recipeBook.getRecipes().stream().filter(item -> item.getRecipeIngredients().stream().anyMatch(innerItem -> innerItem.getName()
                .equals(IngredientName.Strawberry))).collect(Collectors.toList());
        warningQuantity = auxRecipes.stream().map(item -> drinkSize / item.getQuantity() * warningFactor * item.getRecipeIngredients().stream()
                .filter(innerItem -> innerItem.getName().equals(IngredientName.Strawberry)).findAny().map(innerItem -> innerItem.getQuantity()).get()).max(Integer::compare).get();
        inventory.addIngredient(new InventoryIngredient(IngredientName.Strawberry, 5000, MeasureUnit.Gram, warningQuantity, 0.0045f));
        auxRecipes = recipeBook.getRecipes().stream().filter(item -> item.getRecipeIngredients().stream().anyMatch(innerItem -> innerItem.getName()
                .equals(IngredientName.Banana))).collect(Collectors.toList());
        warningQuantity = auxRecipes.stream().map(item -> drinkSize / item.getQuantity() * warningFactor * item.getRecipeIngredients().stream()
                .filter(innerItem -> innerItem.getName().equals(IngredientName.Banana)).findAny().map(innerItem -> innerItem.getQuantity()).get()).max(Integer::compare).get();
        inventory.addIngredient(new InventoryIngredient(IngredientName.Banana, 6000, MeasureUnit.Gram, warningQuantity, 0.001f));
        auxRecipes = recipeBook.getRecipes().stream().filter(item -> item.getRecipeIngredients().stream().anyMatch(innerItem -> innerItem.getName()
                .equals(IngredientName.Mango))).collect(Collectors.toList());
        warningQuantity = auxRecipes.stream().map(item -> drinkSize / item.getQuantity() * warningFactor * item.getRecipeIngredients().stream()
                .filter(innerItem -> innerItem.getName().equals(IngredientName.Mango)).findAny().map(innerItem -> innerItem.getQuantity()).get()).max(Integer::compare).get();
        inventory.addIngredient(new InventoryIngredient(IngredientName.Mango, 7000, MeasureUnit.Gram, warningQuantity, 0.0025f));

        salesReport = new Sales();
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public static float sellDrink(DrinkName drinkName, DrinkSize drinkSize) {
        Recipe recipe = recipeBook.getRecipe(drinkName);
        if (recipe != null) {
            int factor = drinkSize.getValue() / recipe.getQuantity();

            if (prepareDrink(recipe, factor, false)) {
                prepareDrink(recipe, factor, true);
                float drinkCost = recipe.getUnitCost() * drinkSize.getValue();
                salesReport.addSale(drinkName, drinkSize, recipe.getRetailPrice(drinkSize), drinkCost);
                return recipe.getRetailPrice(drinkSize);
            }
            return 0;
        }
        return 0;
    }

    private static boolean prepareDrink(Recipe recipe, int factor, boolean affectInventory) {
        InventoryIngredient inventoryIngredient;
        int balance;
        boolean canPrepareDrink = true;

        for (RecipeIngredient item : recipe.getRecipeIngredients()) {
            inventoryIngredient = inventory.getIngredient(item.getName());
            balance = inventoryIngredient.getQuantity() - (item.getQuantity() * factor);
            if (balance >= 0) {
                if (affectInventory)
                    inventoryIngredient.setQuantity(balance);
            }
            else {
                canPrepareDrink = false;
                break;
            }
        }
        return canPrepareDrink;
    }

    public static Sales getSales() {
        return salesReport;
    }

    public static List<SaleSummary> getSalesReport() {
        return salesReport.getSalesSummary();
    }
}
