package product;

import interfaces.Discountable;
import material.Material;

import java.math.BigDecimal;

public class Furniture implements Discountable {

    private String name;
    protected BigDecimal basePrice;
    protected Material[] materials;

    public Furniture(String name, BigDecimal basePrice, Material[] materials) {
        this.name = name;
        this.basePrice = basePrice;
        this.materials = materials;
    }

    public Furniture() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Material[] getMaterials() {
        return materials;
    }

    public void setMaterials(Material[] materials) {
        this.materials = materials;
    }

    public BigDecimal calculateTotalCost() {
        BigDecimal total = basePrice;

        if (materials != null) {
            for (Material m : materials) {
                total = total.add(m.calculateTotalCost());
            }
        }

        return total;
    }

    @Override
    public void applyDiscount(BigDecimal percentage) {
        BigDecimal discount = basePrice.multiply(percentage).divide(BigDecimal.valueOf(100));
        basePrice = basePrice.subtract(discount);
    }

    @Override
    public String toString() {
        return getName() + " (price: " + basePrice + ")";
    }
}
