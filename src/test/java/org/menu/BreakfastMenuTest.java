package org.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreakfastMenuTest {

    BreakfastMenu breakfastMenu;

    @BeforeEach
    void init() {
        breakfastMenu = new BreakfastMenu();
        breakfastMenu.orderedMainDishes = new HashMap<>();
        breakfastMenu.orderedSideDishes = new HashMap<>();
        breakfastMenu.orderedDrinks = new HashMap<>();
    }

    @Test
    void populateMenu() {
        breakfastMenu.populateMenu();
        assertEquals(breakfastMenu.mainDishes.size() + breakfastMenu.sideDishes.size() + breakfastMenu.drinks.size(), breakfastMenu.menu.size());
    }

    @Test
    void validateOrderTestMainMissing() {
        try {
            breakfastMenu.validateOrder();
        } catch (Exception e) {
            assertEquals("Main is missing", e.getMessage());
        }
    }

    @Test
    void validateOrderTestSideMissing() {
        breakfastMenu.orderedMainDishes.put("Eggs", 1);
        try {
            breakfastMenu.validateOrder();
        } catch (Exception e) {
            assertEquals("Side is missing", e.getMessage());
        }
    }

    @Test
    void validateOrderTestMultipleSides() {
        breakfastMenu.orderedMainDishes.put("Eggs", 1);
        breakfastMenu.orderedSideDishes.put("Toast", 3);
        try {
            breakfastMenu.validateOrder();
        } catch (Exception e) {
            assertEquals("Toast cannot be ordered more than once", e.getMessage());
        }
    }

    @Test
    void validateOrderTestMultipleMains() {
        breakfastMenu.orderedMainDishes.put("Eggs", 3);
        breakfastMenu.orderedSideDishes.put("Toast", 1);
        try {
            breakfastMenu.validateOrder();
        } catch (Exception e) {
            assertEquals("Eggs cannot be ordered more than once", e.getMessage());
        }
    }

    @Test
    void validateOrderTestMultipleDrinks() {
        breakfastMenu.orderedMainDishes.put("Eggs", 1);
        breakfastMenu.orderedSideDishes.put("Toast", 1);
        breakfastMenu.orderedDrinks.put("Coffee", 4);
        try {
            breakfastMenu.validateOrder();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}