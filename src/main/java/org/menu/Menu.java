package org.menu;

import java.util.ArrayList;
import java.util.Map;

public abstract class Menu {
    Map<Integer, String> menu;
    ArrayList<String> finalOrder = new ArrayList<>();

    public abstract OrderingMenu getOrderingMenu(FoodTypes foodType) throws Exception;

    public abstract DishTypes getDishType(int order) throws Exception;
}
