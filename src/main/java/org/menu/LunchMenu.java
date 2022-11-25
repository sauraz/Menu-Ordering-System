package org.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LunchMenu extends OrderingMenuImpl implements OrderingMenu {

    public LunchMenu() {
        this.mainDishes = new HashMap<>();
        this.sideDishes = new HashMap<>();
        this.drinks = new HashMap<>();
        this.menu = new HashMap<>();
        populateMenu();
    }

    /**
     * populateMenu - populates the default menu
     */
    @Override
    public void populateMenu() {
        this.mainDishes.put(1, "Sandwich");
        this.sideDishes.put(2, "Chips");
        this.drinks.put(3, "Soda");
        this.menu.putAll(this.mainDishes);
        this.menu.putAll(this.sideDishes);
        this.menu.putAll(this.drinks);
    }

    /**
     * validateOrder validates the ordered items as per Lunch rules set.
     *
     * @throws Exception if ordered items does satisfy the corresponding rules of Lunch Menu
     */
    @Override
    public void validateOrder() throws Exception {

        // Each order must contain a main
        if (this.orderedMainDishes.size() == 0) {
            throw new Exception("Main is missing");
        }

        // Each order must contain a side
        if (this.orderedSideDishes.size() == 0) {
            throw new Exception("Side is missing");
        }

        // Checks if each item ordered is ordered multiple times.
        // At dinner, multiple mains cannot be ordered
        List<String> mainDish = this.orderedMainDishes.entrySet().stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(Map.Entry::getKey).toList();
        if (this.orderedMainDishes.size() > 1 || (mainDish.size() > 0)) {
            throw new Exception(String.format("%s cannot be ordered more than once", mainDish.get(0)));
        }

        // If no drink is ordered, water should be provided
        if (this.orderedDrinks.size() == 0) {
            this.orderedDrinks.put(Constants.DEFAULT_DRINK, 1);
        }
    }
}
