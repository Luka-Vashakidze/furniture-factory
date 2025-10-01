package com.furniture.furniturefactory.order;

import com.furniture.furniturefactory.enums.OrderPriority;
import com.furniture.furniturefactory.enums.OrderStatus;
import com.furniture.furniturefactory.exceptions.PaymentException;
import com.furniture.furniturefactory.interfaces.Deliverable;
import com.furniture.furniturefactory.interfaces.Discountable;
import com.furniture.furniturefactory.interfaces.Payable;
import com.furniture.furniturefactory.product.Furniture;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order implements Discountable, Deliverable, Payable {

    private static int orderCounter;

    static {
        orderCounter = 0;
    }

    private Integer orderId;
    private String customerName;
    private List<Furniture> items;
    private LocalDate orderDate;
    private boolean delivered;
    private boolean paid;
    private OrderStatus status = OrderStatus.NEW;
    private OrderPriority priority = OrderPriority.NORMAL;

    public Order(int orderId, String customerName, List<Furniture> items, LocalDate orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
        this.orderDate = orderDate;
        orderCounter++;
    }

    public Order() {
        orderCounter++;
    }

    public static int getOrderCounter() {
        return orderCounter;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Furniture> getItems() {
        return items;
    }

    public void setItems(List<Furniture> items) {
        this.items = items;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        if (status != null) this.status = status;
    }

    public OrderPriority getPriority() {
        return priority;
    }

    public void setPriority(OrderPriority priority) {
        if (priority != null) this.priority = priority;
    }

    public BigDecimal calculateTotalPrice() {
        if (items == null) return BigDecimal.ZERO;
        return items.stream()
                .filter(f -> f != null)
                .map(Furniture::calculateTotalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void applyDiscount(BigDecimal percentage) {
        if (items == null || items.isEmpty()) return;
        items.stream()
                .filter(f -> f instanceof Discountable)
                .map(f -> (Discountable) f)
                .forEach(d -> d.applyDiscount(percentage));
    }

    @Override
    public void deliver() {
        System.out.println("Order delivered.");
    }

    @Override
    public void pay(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentException("Payment amount must be positive.");
        }
        BigDecimal total = calculateTotalPrice();
        if (total == null) {
            throw new PaymentException("Unable to determine total price for payment.");
        }
        if (amount.compareTo(total) < 0) {
            throw new PaymentException("Insufficient payment. Required: " + total + ", provided: " + amount);
        }
        paid = true;
        System.out.println("Paid: " + amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Order other))
            return false;
        return Objects.equals(this.orderId, other.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}