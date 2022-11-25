package org.menu;

public interface OrderingMenu {
    void processOrder(int[] orders) throws Exception;

    void populateMenu();

    void showFinalOrder();

    void validateOrder() throws Exception;

    void generateFinalOrder();

}
