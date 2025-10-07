package com.furniture.furniturefactory.product;

import com.furniture.furniturefactory.interfaces.Buildable;
import com.furniture.furniturefactory.material.Material;

import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Chair extends Furniture implements Buildable {
    private static final Logger logger = LogManager.getLogger(Chair.class);

    private int legs;
    private boolean hasArmrest;
    private double weightCapacity;

    public Chair(String name, BigDecimal basePrice, List<Material> materials, int legs, boolean hasArmrest, double weightCapacity) {
        super(name, basePrice, materials);
        this.legs = legs;
        this.hasArmrest = hasArmrest;
        this.weightCapacity = weightCapacity;
    }

    public Chair() {
        super();
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public boolean hasArmrest() {
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

    @Override
    public BigDecimal calculateTotalCost() {
        BigDecimal total = super.calculateTotalCost();
        if (hasArmrest) {
            total = total.add(BigDecimal.valueOf(15));
        }
        return total;
    }

    @Override
    public void assemble() {
        logger.info("Chair assembled. {}", getName());
    }

    @Override
    public void disassemble() {
        logger.info("Chair disassembled. {}", getName());
    }

    @Override
    public String toString() {
        return "chair: " + getName() + ", legs: " + legs + ", armrest: " + hasArmrest;
    }
}
