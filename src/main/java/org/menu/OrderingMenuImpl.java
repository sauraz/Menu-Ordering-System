package org.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OrderingMenuImpl extends Menu {
    Map<Integer, String> mainDishes;
    Map<Integer, String> sideDishes;
    Map<Integer, String> drinks;
    Map<Integer, String> desserts;

    public Map<String, Integer> getOrderedMainDishes() {
        return orderedMainDishes;
    }

    public Map<String, Integer> getOrderedSideDishes() {
        return orderedSideDishes;
    }

    public Map<String, Integer> getOrderedDrinks() {
        return orderedDrinks;
    }

    public Map<String, Integer> getOrderedDesserts() {
        return orderedDesserts;
    }

    Map<String, Integer> orderedMainDishes;
    Map<String, Integer> orderedSideDishes;
    Map<String, Integer> orderedDrinks;
    Map<String, Integer> orderedDesserts;

    /**
     * @param foodType provides the information to which menu object is requested
     * @return corresponding menu for the foodType provided
     * @throws Exception if invalid menu is requested
     */
    @Override
    public OrderingMenu getOrderingMenu(FoodTypes foodType) throws Exception {
        switch (foodType) {
            case BREAKFAST: {
                return new BreakfastMenu();
            }
            case LUNCH: {
                return new LunchMenu();
            }
            case DINNER: {
                return new DinnerMenu();
            }
        }
        throw new Exception("Invalid Food Type");
    }

    /**
     * @param order requires orderID
     * @return corresponding type of the orderID provided
     * @throws Exception if the orderID is invalid
     */
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

    /**
     * Maps each item ordered to it's type of food
     *
     * @param orders contains the list of ordered items in integer
     * @throws Exception if the orderID is invalid is present in the list
     */
    public void processOrder(int[] orders) throws Exception {
        orderedMainDishes = new HashMap<>();
        orderedSideDishes = new HashMap<>();
        orderedDrinks = new HashMap<>();
        orderedDesserts = new HashMap<>();
        Map<Integer, Integer> mapDishIdCount = getCountOfEachDish(orders);
        for (Map.Entry<Integer, Integer> e : mapDishIdCount.entrySet()) {
            String res = this.menu.get(e.getKey());
            switch (this.getDishType(e.getKey())) {
                case MAIN: {
                    orderedMainDishes.put(res, e.getValue());
                    break;
                }
                case SIDE: {
                    orderedSideDishes.put(res, e.getValue());
                    break;
                }
                case DRINK: {
                    orderedDrinks.put(res, e.getValue());
                    break;
                }
                case DESSERT: {
                    orderedDesserts.put(res, e.getValue());
                    break;
                }
            }
        }
    }

    /**
     * generateFinalOrder - builds the final order following the below rule
     * The system should always return items in the following order: meal, side, drink
     */
    public void generateFinalOrder() {
        this.finalOrder.addAll(getFormattedOrder(orderedMainDishes));
        this.finalOrder.addAll(getFormattedOrder(orderedSideDishes));
        this.finalOrder.addAll(getFormattedOrder(orderedDrinks));
        this.finalOrder.addAll(getFormattedOrder(orderedDesserts));
    }

    /**
     * @param dishes requires map of items and their count
     * @return formatted string list for output
     */
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

    /**
     * Displays the final order
     */
    public void showFinalOrder() {
        Iterator<String> it = this.finalOrder.iterator();
        if (it.hasNext()) {
            System.out.print(it.next());
        }
        while (it.hasNext()) {
            System.out.print(", " + it.next());
        }
    }

    /**
     * @param orders contains the list of ordered items in integer
     * @return map of count of each item ordered
     */
    public Map<Integer, Integer> getCountOfEachDish(int[] orders) {
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
