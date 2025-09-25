package order;

import enums.OrderPriority;
import enums.OrderStatus;
import java.math.BigDecimal;

public record OrderSummary(int orderId, BigDecimal total, OrderStatus status, OrderPriority priority) {
    public String pretty() {
        return "OrderSummary{id=%d, total=%s, status=%s, priority=%s}".formatted(orderId, total, status, priority);
    }
}