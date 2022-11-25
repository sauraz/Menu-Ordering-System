package org.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OrderingMenuImpl extends Menu {
    Map<Integer, String> mainDishes;
    Map<Integer, String> sideDishes;
    Map<Integer, String> drinks;
    Map<Integer, String> desserts;
    Map<String, Integer> orderedMainDishes;
    Map<String, Integer> orderedSideDishes;
    Map<String, Integer> orderedDrinks;
    Map<String, Integer> orderedDesserts;

    @Override
    public OrderingMenu getOrderingMenu(FoodTypes foodType) throws Exception {
        switch (foodType) {
            case BREAKFAST -> {
                return new BreakfastMenu();
            }
            case LUNCH -> {
                return new LunchMenu();
            }
            case DINNER -> {
                return new DinnerMenu();
            }
        }
        throw new Exception("Invalid Food Type");
    }

    @Override
    public DishTypes getDishType(int order) throws Exception {
        if (this.mainDishes.containsKey(order)) {
            return DishTypes.MAIN;
        } else if (this.sideDishes.containsKey(order)) {
            return DishTypes.SIDE;
        } else if (this.drinks.containsKey(order)) {
            return DishTypes.DRINK;
        } else if (this.desserts.containsKey(order)) {
            return DishTypes.DESSERT;
        } else {
            throw new Exception("Invalid Dish Type provided");
        }
    }

    public void processOrder(int[] orders) throws Exception {
        orderedMainDishes = new HashMap<>();
        orderedSideDishes = new HashMap<>();
        orderedDrinks = new HashMap<>();
        orderedDesserts = new HashMap<>();
        Map<Integer, Integer> mapDishIdCount = getCountOfEachDish(orders);
        for (Map.Entry<Integer, Integer> e : mapDishIdCount.entrySet()) {
            String res = this.menu.get(e.getKey());
            switch (this.getDishType(e.getKey())) {
                case MAIN -> orderedMainDishes.put(res, e.getValue());
                case SIDE -> orderedSideDishes.put(res, e.getValue());
                case DRINK -> orderedDrinks.put(res, e.getValue());
                case DESSERT -> orderedDesserts.put(res, e.getValue());
            }
        }
    }

    public void generateFinalOrder() {
        this.finalOrder.addAll(getFormattedOrder(orderedMainDishes));
        this.finalOrder.addAll(getFormattedOrder(orderedSideDishes));
        this.finalOrder.addAll(getFormattedOrder(orderedDrinks));
        this.finalOrder.addAll(getFormattedOrder(orderedDesserts));
    }

    public ArrayList<String> getFormattedOrder(Map<String, Integer> dishes) {
        ArrayList<String> dishesOrdered = new ArrayList<>();
        for (Map.Entry<String, Integer> e : dishes.entrySet()) {
            String res = e.getKey();
            if (e.getValue() > 1)
                res += String.format("(%s)", e.getValue());
            dishesOrdered.add(res);
        }
        return dishesOrdered;
    }

    public void showFinalOrder() {
        System.out.println(Arrays.toString(this.finalOrder.toArray()));
    }

    public Map<Integer, Integer> getCountOfEachDish(int[] orders) throws Exception {
        Map<Integer, Integer> mapDishIdCount = new HashMap<>();
        for (int order : orders) {
            if (this.menu.containsKey(order)) {
                if (mapDishIdCount.containsKey(order))
                    mapDishIdCount.put(order, mapDishIdCount.get(order) + 1);
                else
                    mapDishIdCount.put(order, 1);
            }
        }
        return mapDishIdCount;
    }
}
