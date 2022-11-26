package org.menu;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderingMenuImplTest {
    OrderingMenu orderingMenu;

    @Test
    void getOrderingMenu() {
        try {
            orderingMenu = new OrderingMenuImpl().getOrderingMenu(FoodTypes.LUNCH);
            assertEquals(orderingMenu.getClass(), LunchMenu.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void processOrder() {
        try {
            orderingMenu = new OrderingMenuImpl().getOrderingMenu(FoodTypes.DINNER);
            orderingMenu.processOrder(new int[]{1, 2, 3, 4});
            orderingMenu.validateOrder();
            assertEquals(1, orderingMenu.getOrderedMainDishes().size());
            assertEquals(1, orderingMenu.getOrderedSideDishes().size());
            assertEquals(2, orderingMenu.getOrderedDrinks().size());
            assertEquals(1, orderingMenu.getOrderedDesserts().size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getCountOfEachDish() {
        try {
            orderingMenu = new OrderingMenuImpl().getOrderingMenu(FoodTypes.DINNER);
            Map<Integer, Integer> mapOfCount = orderingMenu.getCountOfEachDish(new int[]{1, 2, 3, 4});
            assertEquals(1, mapOfCount.get(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}