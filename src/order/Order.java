package order;

import interfaces.Deliverable;
import interfaces.Discountable;
import interfaces.Payable;
import product.Furniture;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Order implements Discountable, Deliverable, Payable {

    private static int orderCounter;

    private Integer orderId;
    private String customerName;
    private Furniture[] items;
    private LocalDate orderDate;

    private boolean delivered;
    private boolean paid;
    static {
        orderCounter = 0;
    }

    public Order(int orderId, String customerName, Furniture[] items, LocalDate orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
        this.orderDate = orderDate;
        orderCounter++;
    }

    public Order() {
        orderCounter++;
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

    public Furniture[] getItems() {
        return items;
    }

    public void setItems(Furniture[] items) {
        this.items = items;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal calculateTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;

        if (items != null) {
            for (Furniture f : items) {
                if (f != null) {
                    total = total.add(f.calculateTotalCost());
                }
            }
        }

        return total;
    }

    public static int getOrderCounter() {
        return orderCounter;
    }

    @Override
    public void applyDiscount(BigDecimal percentage) {
        for (Furniture f : items){
            if (f instanceof Discountable d) {
                d.applyDiscount(percentage);
            }
        }
    }
    @Override
    public void deliver() {
        System.out.println("Order delivered.");
    }
    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Paid: " + amount);
    }
}
