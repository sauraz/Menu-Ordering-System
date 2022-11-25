package org.menu;

import java.util.Arrays;
import java.util.Scanner;

public class Processor {
    public void processInput() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] split = input.split(" ", 2);
        if (split.length < 2 || split[1].trim().length() == 0) {
            throw new Exception("Main is missing, Side is missing");
        }
        String foodType = split[0];
        int[] orders = Arrays.stream(split[1].split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        OrderingMenu menu = new OrderingMenuImpl().getOrderingMenu(FoodTypes.getFoodType(foodType));
        menu.processOrder(orders);
        menu.validateOrder();
        menu.generateFinalOrder();
        menu.showFinalOrder();
    }

}

