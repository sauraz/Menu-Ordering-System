package org.menu;

public enum FoodTypes {
    BREAKFAST,
    LUNCH,
    DINNER;

    /**
     * @param foodType is provided in string
     * @return an Enum for the corresponding string foodType
     * @throws Exception if provided stringType does not have a corresponding Enum type
     */
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
