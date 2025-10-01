package com.furniture.furniturefactory.service;

import com.furniture.furniturefactory.exceptions.AssemblyFailedException;
import com.furniture.furniturefactory.exceptions.InsufficientMaterialsException;
import com.furniture.furniturefactory.exceptions.InvalidOrderException;
import com.furniture.furniturefactory.interfaces.Buildable;
import com.furniture.furniturefactory.order.Order;
import com.furniture.furniturefactory.order.OrderSummary;
import com.furniture.furniturefactory.product.Furniture;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

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
    public List<OrderSummary> processOrders(
            List<Order> orders,
            Predicate<Order> filter,
            Function<Order, BigDecimal> totalFn,
            Supplier<BigDecimal> discountSupplier,
            UnaryOperator<List<Furniture>> itemsOp,
            BiFunction<Order, BigDecimal, Order> adjuster,
            Consumer<OrderSummary> afterEach,
            BiConsumer<Order, Exception> onError
    ) {
        List<OrderSummary> results = new ArrayList<>();
        if (orders == null) return results;

        for (Order o : orders) {
            try {
                if (o == null || !filter.test(o)) continue;

                o.setItems(itemsOp.apply(o.getItems()));

                BigDecimal base = totalFn.apply(o);
                BigDecimal discountPercent = discountSupplier.get();
                if (discountPercent != null && discountPercent.compareTo(BigDecimal.ZERO) > 0) {
                    o.applyDiscount(discountPercent);
                }

                Order adjusted = adjuster.apply(o, o.calculateTotalPrice());

                if (!adjusted.getStatus().isTerminal()) {
                    adjusted.setStatus(adjusted.getStatus().next());
                }

                BigDecimal finalTotal = adjusted.getPriority().applySurcharge(adjusted.calculateTotalPrice());

                OrderSummary summary = new OrderSummary(
                        adjusted.getOrderId(),
                        finalTotal,
                        adjusted.getStatus(),
                        adjusted.getPriority()
                );
                results.add(summary);
                if (afterEach != null) afterEach.accept(summary);

            } catch (Exception ex) {
                if (onError != null) onError.accept(o, ex);
            }
        }

        return results;
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