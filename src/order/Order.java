package order;

import product.Furniture;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {

    private int orderId;
    private String customerName;
    private Furniture[] items;
    private LocalDate orderDate;

    private static int orderCounter;

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
}
