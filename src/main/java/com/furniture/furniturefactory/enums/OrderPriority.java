package com.furniture.furniturefactory.enums;

import java.math.BigDecimal;

public enum OrderPriority {
    LOW("Low", 14, BigDecimal.ZERO),
    NORMAL("Normal", 7, BigDecimal.ZERO),
    HIGH("High", 3, BigDecimal.valueOf(5)),
    URGENT("Urgent", 1, BigDecimal.valueOf(10));

    private final String label;
    private final int deadlineDays;
    private final BigDecimal surchargePercent;

    static {
        if (LOW.deadlineDays <= 0) {
            throw new IllegalStateException("Deadline days must be positive");
        }
    }

    OrderPriority(String label, int deadlineDays, BigDecimal surchargePercent) {
        this.label = label;
        this.deadlineDays = deadlineDays;
        this.surchargePercent = surchargePercent;
    }

    public String label() { return label; }
    public int deadlineDays() { return deadlineDays; }

    public BigDecimal applySurcharge(BigDecimal amount) {
        if (amount == null) return BigDecimal.ZERO;
        return amount.add(amount.multiply(surchargePercent).divide(BigDecimal.valueOf(100)));
    }
}