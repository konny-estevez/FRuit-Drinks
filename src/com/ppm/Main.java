package com.ppm;

import com.ppm.entities.*;
import com.ppm.util.Keyin;
import com.ppm.businessLogic.Inventory;
import com.ppm.businessLogic.Sales;
import com.ppm.businessLogic.Shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Fruit Drinks Control System");
        System.out.println();
        System.out.println("Loading recipes`");
        System.out.println();
        Shop.loadRecipes();
        System.out.println("Recipes loaded...");
        System.out.println();
        System.out.println("Loading initial inventory");
        System.out.println();
        Shop.loadInventory();
        System.out.println("Initial inventory loaded...");
        System.out.println();
        mainMenu();
    }

    private static  void mainMenu() {
        char option;

        do {
            System.out.println("|           Fruit Drinks Menu                           |");
            System.out.println("|                                                       |");
            System.out.println("| Options:                                              |");
            System.out.println("|        1. Display Inventory                           |");
            System.out.println("|        2. Sell Strawberry Drink                       |");
            System.out.println("|        3. Sell Banana Drink                           |");
            System.out.println("|        4. Sell Mango Drink                            |");
            System.out.println("|        5. Sell Strawberry & Banana Drink              |");
            System.out.println("|        6. Sell Strawberry & Mango Drink               |");
            System.out.println("|        7. Sell Banana & Mango Drink                   |");
            System.out.println("|        8. Sell Full Mix Drink                         |");
            System.out.println("|        9. Detailed Sales List                         |");
            System.out.println("|        0. Sales Report                                |");
            System.out.println("|        *. Exit                                        |");
            option =  Keyin.inInt(" Select option: ");
            System.out.println();

            switch (option) {
                case '1':
                    displayInventory(Shop.getInventory());
                    break;
                case '2':
                    sellDrink(DrinkName.Strawberry, drinkSizeMenu());
                    break;
                case '3':
                    sellDrink(DrinkName.Banana, drinkSizeMenu());
                    break;
                case '4':
                    sellDrink(DrinkName.Mango, drinkSizeMenu());
                    break;
                case '5':
                    sellDrink(DrinkName.StrawberryBanana, drinkSizeMenu());
                    break;
                case '6':
                    sellDrink(DrinkName.StrawberryMango, drinkSizeMenu());
                    break;
                case '7':
                    sellDrink(DrinkName.BananaMango, drinkSizeMenu());
                    break;
                case '8':
                    sellDrink(DrinkName.SuperMix, drinkSizeMenu());
                    break;
                case '9':
                    displaySales(Shop.getSales());
                    break;
                case '0':
                    displaySalesReport(Shop.getSalesReport());
                    break;
                case '*':
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
            System.out.println();
        } while (option != '*');
    }

    private static DrinkSize drinkSizeMenu() {
        char option;
        DrinkSize drinkSize = DrinkSize.Medium;
        List<Character> validOptions = new ArrayList<>();
        validOptions.add('a');
        validOptions.add('b');
        validOptions.add('c');

        do {
            System.out.println("|           Drinks Size Menu                            |");
            System.out.println("|                                                       |");
            System.out.println("| Options:                                              |");
            System.out.println("|        a. Small Size (200 ml)                         |");
            System.out.println("|        b. Medium Size (300 ml)                        |");
            System.out.println("|        c. Large Size (500 ml)                         |");
            option = Keyin.inInt(" Select option: ");
            System.out.println();

            switch (option) {
                case 'a':
                    drinkSize = DrinkSize.Small;
                    break;
                case 'b':
                    drinkSize = DrinkSize.Medium;
                    break;
                case 'c':
                    drinkSize = DrinkSize.Large;
                    break;
            }
            System.out.println();
        } while (!validOptions.contains(option));
        return drinkSize;
    }

    private static void displayInventory(Inventory inventory) {
        System.out.println("Current Inventory");
        System.out.println();
        boolean displayUnitCost = false;

        for (InventoryIngredient ingredient : inventory.getIngredients()) {
            System.out.print("IngredientName: " + ingredient.getName() + ", Quantity: " + ingredient.getQuantity() + " " + ingredient.getMeasureUnit());
            if (displayUnitCost)
                System.out.print(", Unit Cost: " + ingredient.getUnitCost());
            if (ingredient.getQuantity() < ingredient.getMinimumQuantity())
                System.out.println(", Warning: low stock");
            else
                System.out.println();
        }
        System.out.println();
        System.out.println("Inventory finished...");
        System.out.println();
    }

    private static void sellDrink(DrinkName drinkName, DrinkSize drinkSize) {
        System.out.println("Preparing " + drinkName.name() + " drink");
        System.out.println();
        float retailPrice = Shop.sellDrink(drinkName, drinkSize);
        if (retailPrice > 0)
            System.out.println(drinkSize + " ml of " + drinkName + " drink ready..., " + retailPrice + " dollars please");
        else
            System.out.println("Can not prepare the drink, insufficient ingredients...");
        System.out.println();
    }

    private static void displaySales(Sales sales) {
        System.out.println("Current Sales Details");
        System.out.println();
        if (sales.getSales().isEmpty()) {
            System.out.println("Yuo do not have any sales yet.");
        }
        else {
            for (Sale sale : sales.getSales()) {
                System.out.println(" Dste: " + sale.getDate() + ", Drink Name: " + sale.getDrink().getName() + ", Quantity: " + sale.getDrink().getSize() + ", Price: "
                        + sale.getDrink().getRetailPrice());
            }
        }
        System.out.println();
        System.out.println("Detailed sales report finished...");
        System.out.println();
    }

    private static void displaySalesReport(List<SaleSummary> salesReport) {
        System.out.println("Current Sales Details");
        System.out.println();
        if (salesReport.isEmpty()) {
            System.out.println("Yuo do not have any sales yet.");
        }
        else {
            for (SaleSummary saleSummary : salesReport) {
                System.out.println(" Dste: " + saleSummary.getDate() + ", Total Drinks: " + saleSummary.getTotalDrinks() + ", Total Price: $ " + saleSummary.getTotalPrice() + ", Total Cost: $ " + saleSummary.getTotalCost());
            }
        }
        System.out.println();
        System.out.println("Sales report finished...");
        System.out.println();
    }
}
