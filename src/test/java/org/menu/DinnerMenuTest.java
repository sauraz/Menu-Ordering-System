package org.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DinnerMenuTest {

    DinnerMenu dinnerMenu;

    @BeforeEach
    void init() {
        dinnerMenu = new DinnerMenu();
        dinnerMenu.orderedMainDishes = new HashMap<>();
        dinnerMenu.orderedSideDishes = new HashMap<>();
        dinnerMenu.orderedDrinks = new HashMap<>();
        dinnerMenu.orderedDesserts = new HashMap<>();
    }

    @Test
    void populateMenu() {
        dinnerMenu.populateMenu();
        assertEquals(dinnerMenu.mainDishes.size() + dinnerMenu.sideDishes.size() + dinnerMenu.drinks.size() + dinnerMenu.desserts.size(), dinnerMenu.menu.size());
    }

    @Test
    void validateOrderTestMainMissing() {
        try {
            dinnerMenu.validateOrder();
        } catch (Exception e) {
            assertEquals("Main is missing", e.getMessage());
        }
    }

    @Test
    void validateOrderTestSideMissing() {
        dinnerMenu.orderedMainDishes.put("Sandwich", 1);
        try {
            dinnerMenu.validateOrder();
        } catch (Exception e) {
            assertEquals("Side is missing", e.getMessage());
        }
    }

    @Test
    void validateOrderTestDessertMissing() {
        dinnerMenu.orderedMainDishes.put("Sandwich", 1);
        dinnerMenu.orderedSideDishes.put("Chips", 1);
        try {
            dinnerMenu.validateOrder();
        } catch (Exception e) {
            assertEquals("Dessert is missing", e.getMessage());
        }
    }

    @Test
    void validateOrderTestMultipleSides() {
        dinnerMenu.orderedMainDishes.put("Sandwich", 1);
        dinnerMenu.orderedSideDishes.put("Chips", 3);
        dinnerMenu.orderedDesserts.put("Cake", 1);
        try {
            dinnerMenu.validateOrder();
        } catch (Exception e) {
            assertEquals("Chips cannot be ordered more than once", e.getMessage());
        }
    }

    @Test
    void validateOrderTestMultipleMains() {
        dinnerMenu.orderedMainDishes.put("Sandwich", 3);
        dinnerMenu.orderedSideDishes.put("Chips", 1);
        dinnerMenu.orderedDesserts.put("Cake", 1);
        try {
            dinnerMenu.validateOrder();
        } catch (Exception e) {
            assertEquals("Sandwich cannot be ordered more than once", e.getMessage());
        }
    }

    @Test
    void validateOrderTestMultipleDrinks() {
        dinnerMenu.orderedMainDishes.put("Sandwich", 1);
        dinnerMenu.orderedSideDishes.put("Chips", 1);
        dinnerMenu.orderedDrinks.put("Soda", 4);
        dinnerMenu.orderedDesserts.put("Cake", 1);
        try {
            dinnerMenu.validateOrder();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}