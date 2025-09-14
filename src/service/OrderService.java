package service;

import exceptions.AssemblyFailedException;
import exceptions.InsufficientMaterialsException;
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

        public void assembleFurniture(Buildable buildableItem) {
            if (buildableItem == null) {
                throw new AssemblyFailedException("Nothing to assemble.");
            }

            String itemName = "Buildable item";
            if (buildableItem instanceof Furniture f) {
                itemName = f.getName();
                if (f.getMaterials() == null || f.getMaterials().length == 0) {
                    throw new InsufficientMaterialsException("no materials for : " + itemName);
                }
            }

            try (AssemblySession session = new AssemblySession(itemName)) {
                System.out.println("Starting assembly .");
                buildableItem.assemble();
                System.out.println("Asembly finished.");
            } catch (Exception e) {
                System.out.println("Assembly failed : " + e.getMessage());
            }

        }
    }
}
