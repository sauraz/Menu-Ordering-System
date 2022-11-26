package org.menu;

import java.util.Map;

public interface OrderingMenu {
    void processOrder(int[] orders) throws Exception;

    void populateMenu();

    void showFinalOrder();

    void validateOrder() throws Exception;

    void generateFinalOrder();

    public Map<Integer, Integer> getCountOfEachDish(int[] orders);

    public Map<String, Integer> getOrderedMainDishes();

    public Map<String, Integer> getOrderedSideDishes();

    public Map<String, Integer> getOrderedDrinks();

    public Map<String, Integer> getOrderedDesserts();

}
