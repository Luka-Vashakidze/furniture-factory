package product;

import java.math.BigDecimal;

public class Chair {

    private Furniture furniture;
    private int legs;
    private boolean hasArmrest;
    private double weightCapacity; // in kg

    public Chair(Furniture furniture, int legs, boolean hasArmrest, double weightCapacity) {
        this.furniture = furniture;
        this.legs = legs;
        this.hasArmrest = hasArmrest;
        this.weightCapacity = weightCapacity;
    }

    public Chair() {
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public boolean isHasArmrest() {
        return hasArmrest;
    }

    public void setHasArmrest(boolean hasArmrest) {
        this.hasArmrest = hasArmrest;
    }

    public double getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(double weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    public BigDecimal calculateTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;

        if (furniture != null) {
            total = furniture.calculateTotalCost();
        }

        if (hasArmrest) {
            total = total.add(BigDecimal.valueOf(15));
        }

        return total;
    }
}
