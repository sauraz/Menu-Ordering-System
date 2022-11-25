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

    @Override
    public void validateOrder() throws Exception {
        if (this.orderedMainDishes.size() == 0) {
            throw new Exception("Main is missing");
        }
        if (this.orderedSideDishes.size() == 0) {
            throw new Exception("Side is missing");
        }
        if (this.orderedDesserts.size() == 0) {
            throw new Exception("Dessert is missing");
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
        this.orderedDrinks.put(Constants.DEFAULT_DRINK, 1);
    }
}
