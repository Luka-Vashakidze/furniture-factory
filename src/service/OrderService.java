package service;

import interfaces.Buildable;
import order.Order;
import product.Furniture;

import java.math.BigDecimal;

public class OrderService {

    private static int orderCounter;

    static {
        orderCounter = 0;
        System.out.println("OrderService initialized.");
    }

    public OrderService() {
    }

    public void placeOrder(Order order) {
        if (order == null || order.getItems() == null) {
            System.out.println("Cannot place an empty order.");
            return;
        }

        orderCounter++;

        BigDecimal totalPrice = order.calculateTotalPrice();

        System.out.println("Order #" + orderCounter + " for customer: " + order.getCustomerName());
        System.out.println("Total price: $" + totalPrice);
    }

    public static int getOrderCounter() {
        return orderCounter;
    }
    public final void printSummary(Order order) {
        System.out.println("final order summary for: " + order.getCustomerName());
    }
    public static class InterfaceService {

        public void assembleAllFurniture(Furniture[] furnitures) {
            System.out.println("\n Assembling all buildable furniture  ");
            if (furnitures == null)
                return;

            for (Furniture furniture : furnitures) {
                if (furniture instanceof Buildable buildableItem) {
                    buildableItem.assemble();
                }
            }
        }

    }

}
