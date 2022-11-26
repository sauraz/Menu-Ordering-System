package org.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LunchMenuTest {

    LunchMenu lunchMenu;

    @BeforeEach
    void init() {
        lunchMenu = new LunchMenu();
        lunchMenu.orderedMainDishes = new HashMap<>();
        lunchMenu.orderedSideDishes = new HashMap<>();
        lunchMenu.orderedDrinks = new HashMap<>();
    }

    @Test
    void populateMenu() {
        lunchMenu.populateMenu();
        assertEquals(lunchMenu.mainDishes.size() + lunchMenu.sideDishes.size() + lunchMenu.drinks.size(), lunchMenu.menu.size());
    }

    @Test
    void validateOrderTestMainMissing() {
        try {
            lunchMenu.validateOrder();
        } catch (Exception e) {
            assertEquals("Main is missing", e.getMessage());
        }
    }

    @Test
    void validateOrderTestSideMissing() {
        lunchMenu.orderedMainDishes.put("Eggs", 1);
        try {
            lunchMenu.validateOrder();
        } catch (Exception e) {
            assertEquals("Side is missing", e.getMessage());
        }
    }

    @Test
    void validateOrderTestMultipleSides() {
        lunchMenu.orderedMainDishes.put("Eggs", 1);
        lunchMenu.orderedSideDishes.put("Toast", 3);
        try {
            lunchMenu.validateOrder();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void validateOrderTestMultipleMains() {
        lunchMenu.orderedMainDishes.put("Eggs", 3);
        lunchMenu.orderedSideDishes.put("Toast", 1);
        try {
            lunchMenu.validateOrder();
        } catch (Exception e) {
            assertEquals("Eggs cannot be ordered more than once", e.getMessage());
        }
    }

    @Test
    void validateOrderTestMultipleDrinks() {
        lunchMenu.orderedMainDishes.put("Eggs", 1);
        lunchMenu.orderedSideDishes.put("Toast", 1);
        lunchMenu.orderedDrinks.put("Coffee", 4);
        try {
            lunchMenu.validateOrder();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}