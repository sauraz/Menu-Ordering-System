package org.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreakfastMenu extends OrderingMenuImpl implements OrderingMenu {

    public BreakfastMenu() {
        this.mainDishes = new HashMap<>();
        this.sideDishes = new HashMap<>();
        this.drinks = new HashMap<>();
        this.menu = new HashMap<>();
        populateMenu();
    }

    @Override
    public void populateMenu() {
        this.mainDishes.put(1, "Eggs");
        this.sideDishes.put(2, "Toast");
        this.drinks.put(3, "Coffee");
        this.menu.putAll(this.mainDishes);
        this.menu.putAll(this.sideDishes);
        this.menu.putAll(this.drinks);
    }

    @Override
    public void validateOrder() throws Exception {
        if (this.orderedMainDishes.size() == 0) {
            throw new Exception("Main is missing");
        }
        if (this.orderedSideDishes.size() == 0) {
            throw new Exception("Side is missing");
        }
        List<String> mainDish = this.orderedMainDishes.entrySet().stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(Map.Entry::getKey).toList();
        if (this.orderedMainDishes.size() > 1 || (mainDish.size() > 0)) {
            throw new Exception(String.format("%s cannot be ordered more than once", mainDish.get(0)));
        }
        List<String> sideDish = this.orderedSideDishes.entrySet().stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(Map.Entry::getKey).toList();
        if (this.orderedSideDishes.size() > 1 || (sideDish.size() > 0)) {
            throw new Exception(String.format("%s cannot be ordered more than once", sideDish.get(0)));
        }
        if (this.orderedDrinks.size() == 0) {
            this.orderedDrinks.put(Constants.DEFAULT_DRINK, 1);
        }
    }

}
