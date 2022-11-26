package org.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodTypesTest {

    @Test
    void getFoodTypeTest1() {
        try {
            assertEquals(FoodTypes.LUNCH, FoodTypes.getFoodType("lunch"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getFoodTypeTest2() {
        try {
            assertEquals(FoodTypes.LUNCH, FoodTypes.getFoodType("gibberish"));
        } catch (Exception e) {
            assertEquals("Invalid Food Type Provided.", e.getMessage());
        }
    }
}