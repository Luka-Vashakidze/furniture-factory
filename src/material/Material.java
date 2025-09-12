package material;

import java.math.BigDecimal;

public class Material {

    private static int materialCounter;

    private String name;
    private BigDecimal pricePerUnit;
    private int quantityInStock;

    static {
        materialCounter = 0;
    }

    public Material(String name, BigDecimal pricePerUnit, int quantityInStock) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.quantityInStock = quantityInStock;
        materialCounter++;
    }

    public Material() {
        materialCounter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public BigDecimal calculateTotalCost() {
        return pricePerUnit.multiply(BigDecimal.valueOf(quantityInStock));
    }

    public static int getMaterialCounter() {
        return materialCounter;
    }
}
