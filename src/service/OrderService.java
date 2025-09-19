package service;

import exceptions.AssemblyFailedException;
import exceptions.InsufficientMaterialsException;
import exceptions.InvalidOrderException;
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

    public static int getOrderCounter() {
        return orderCounter;
    }

    public void placeOrder(Order order) throws InvalidOrderException {
        if (order == null || order.getItems() == null || order.getItems().isEmpty()) {
            throw new InvalidOrderException("Cannot place an empty order.");
        }

        orderCounter++;

        BigDecimal totalPrice = order.calculateTotalPrice();
        if (totalPrice == null || totalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidOrderException("Order total must be positive.");
        }

        System.out.println("Order #" + orderCounter + " for customer: " + order.getCustomerName());
        System.out.println("Total price: $" + totalPrice);
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
            if (buildableItem instanceof Furniture furniture) {
                itemName = furniture.getName();
                if (furniture.getMaterials() == null || furniture.getMaterials().isEmpty()) {
                    throw new InsufficientMaterialsException("no materials for : " + itemName);
                }
            }

            try (AssemblySession session = new AssemblySession(itemName)) {
                System.out.println("Starting assembly for: " + session.getItemName());
                buildableItem.assemble();
                System.out.println("Assembly finished for: " + session.getItemName());
            } catch (Exception e) {
                System.out.println("Assembly failed : " + e.getMessage());
            }

        }
    }
}