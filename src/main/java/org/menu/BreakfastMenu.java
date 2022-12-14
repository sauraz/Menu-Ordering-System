package org.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BreakfastMenu extends OrderingMenuImpl implements OrderingMenu {

    public BreakfastMenu() {
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
        this.mainDishes.put(1, "Eggs");
        this.sideDishes.put(2, "Toast");
        this.drinks.put(3, "Coffee");
        this.menu.putAll(this.mainDishes);
        this.menu.putAll(this.sideDishes);
        this.menu.putAll(this.drinks);
    }

    /**
     * validateOrder validates the ordered items as per Breakfast rules set.
     *
     * @throws Exception if ordered items does satisfy the corresponding rules of Breakfast Menu
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
        // At breakfast, multiple mains cannot be ordered
        List<String> mainDish = this.orderedMainDishes.entrySet().stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(Map.Entry::getKey).collect(Collectors.toList());
        if (this.orderedMainDishes.size() > 1 || (mainDish.size() > 0)) {
            throw new Exception(String.format("%s cannot be ordered more than once", mainDish.get(0)));
        }

        // Checks if each item ordered is ordered multiple times.
        // At breakfast, multiple sides cannot be ordered
        List<String> sideDish = this.orderedSideDishes.entrySet().stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(Map.Entry::getKey).collect(Collectors.toList());
        if (this.orderedSideDishes.size() > 1 || (sideDish.size() > 0)) {
            throw new Exception(String.format("%s cannot be ordered more than once", sideDish.get(0)));
        }

        // If no drink is ordered, water should be provided
        if (this.orderedDrinks.size() == 0) {
            this.orderedDrinks.put(Constants.DEFAULT_DRINK, 1);
        }
    }

}
