package com.furniture.furniturefactory.interfaces.custom;

import com.furniture.furniturefactory.product.Furniture;
import java.math.BigDecimal;

@FunctionalInterface
public interface PriceRule {
    BigDecimal apply(Furniture item, BigDecimal currentPrice);
}