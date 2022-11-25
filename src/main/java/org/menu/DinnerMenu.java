package org.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DinnerMenu extends OrderingMenuImpl implements OrderingMenu {

    public DinnerMenu() {
        this.mainDishes = new HashMap<>();
        this.sideDishes = new HashMap<>();
        this.drinks = new HashMap<>();
        this.desserts = new HashMap<>();
        this.menu = new HashMap<>();
        populateMenu();
    }

    /**
     * populateMenu - populates the default menu
     */
    @Override
    public void populateMenu() {
        this.mainDishes.put(1, "Steak");
        this.sideDishes.put(2, "Potatoes");
        this.drinks.put(3, "Wine");
        this.desserts.put(4, "Cake");
        this.menu.putAll(this.mainDishes);
        this.menu.putAll(this.sideDishes);
        this.menu.putAll(this.drinks);
        this.menu.putAll(this.desserts);
    }

    /**
     * validateOrder validates the ordered items as per Dinner rules set.
     *
     * @throws Exception if ordered items does satisfy the corresponding rules of Dinner Menu
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

        // At dinner, dessert must be ordered
        if (this.orderedDesserts.size() == 0) {
            throw new Exception("Dessert is missing");
        }

        // Checks if each item ordered is ordered multiple times.
        // At dinner, multiple mains cannot be ordered
        List<String> mainDish = this.orderedMainDishes.entrySet().stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(Map.Entry::getKey).toList();
        if (this.orderedMainDishes.size() > 1 || (mainDish.size() > 0)) {
            throw new Exception(String.format("%s cannot be ordered more than once", mainDish.get(0)));
        }

        // Checks if each item ordered is ordered multiple times.
        // At dinner, multiple sides cannot be ordered
        List<String> sideDish = this.orderedSideDishes.entrySet().stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(Map.Entry::getKey).toList();
        if (this.orderedSideDishes.size() > 1 || (sideDish.size() > 0)) {
            throw new Exception(String.format("%s cannot be ordered more than once", sideDish.get(0)));
        }

        // At dinner, water is always provided
        this.orderedDrinks.put(Constants.DEFAULT_DRINK, 1);
    }
}
