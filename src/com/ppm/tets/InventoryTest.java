package com.ppm.tets;

import com.ppm.businessLogic.Inventory;
import com.ppm.entities.IngredientName;
import com.ppm.entities.InventoryIngredient;
import com.ppm.entities.MeasureUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        inventory.addIngredient(new InventoryIngredient(IngredientName.Banana, 1000, MeasureUnit.Gram, 100, 0.05f));
    }

    @AfterEach
    void tearDown() {
    }

}