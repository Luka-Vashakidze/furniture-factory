package interfaces.custom;

import product.Furniture;
import java.math.BigDecimal;

@FunctionalInterface
public interface PriceRule {
    BigDecimal apply(Furniture item, BigDecimal currentPrice);
}