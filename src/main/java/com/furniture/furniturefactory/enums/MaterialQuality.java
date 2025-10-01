package com.furniture.furniturefactory.enums;

import java.math.BigDecimal;

public enum MaterialQuality {
    BASIC("Basic", BigDecimal.valueOf(1.00)),
    PREMIUM("Premium", BigDecimal.valueOf(1.20)),
    LUXURY("Luxury", BigDecimal.valueOf(1.50));

    private final String label;
    private final BigDecimal multiplier;

    static {
        if (BASIC.multiplier.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalStateException("Multiplier should be >= 1.0");
        }
    }

    MaterialQuality(String label, BigDecimal multiplier) {
        this.label = label;
        this.multiplier = multiplier;
    }

    public String label() { return label; }

    public BigDecimal applyTo(BigDecimal base) {
        return base == null ? BigDecimal.ZERO : base.multiply(multiplier);
    }
}