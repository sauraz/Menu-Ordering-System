package org.menu;

public enum FoodTypes {
    BREAKFAST,
    LUNCH,
    DINNER;

    public static FoodTypes getFoodType(String foodType) throws Exception {
        if (foodType.toUpperCase().trim().equals(BREAKFAST.toString())) {
            return BREAKFAST;
        } else if (foodType.toUpperCase().trim().equals(LUNCH.toString())) {
            return LUNCH;
        } else if (foodType.toUpperCase().trim().equals(DINNER.toString())) {
            return DINNER;
        } else {
            throw new Exception("Invalid Food Type Provided.");
        }
    }
}
