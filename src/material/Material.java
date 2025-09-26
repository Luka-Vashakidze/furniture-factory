package material;

import enums.MaterialQuality;

import java.math.BigDecimal;

public class Material {

    private static int materialCounter;

    static {
        materialCounter = 0;
    }

    private String name;
    private BigDecimal pricePerUnit;
    private int quantityInStock;
    private MaterialQuality quality = MaterialQuality.BASIC;


    public Material(String name, BigDecimal pricePerUnit, int quantityInStock) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.quantityInStock = quantityInStock;
        materialCounter++;
    }

    public Material() {
        materialCounter++;
    }

    public static int getMaterialCounter() {
        return materialCounter;
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
    public MaterialQuality getQuality() {
        return quality;
    }

    public void setQuality(MaterialQuality quality) {
        this.quality = quality;
    }


    public BigDecimal calculateTotalCost() {
        return pricePerUnit.multiply(BigDecimal.valueOf(quantityInStock));
    }
}
