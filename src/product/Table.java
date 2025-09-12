package product;

import interfaces.Buildable;
import material.Material;

import java.math.BigDecimal;

public class Table extends Furniture implements Buildable {

    private int length;
    private int width;
    private int height;
    private boolean hasDrawers;

    public Table(String name, BigDecimal basePrice, Material[] materials,
                 int length, int width, int height, boolean hasDrawers) {
        super(name, basePrice, materials);
        this.length = length;
        this.width = width;
        this.height = height;
        this.hasDrawers = hasDrawers;
    }

    public Table() {
        super();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean hasDrawers() {
        return hasDrawers;
    }

    public void setHasDrawers(boolean hasDrawers) {
        this.hasDrawers = hasDrawers;
    }

    @Override
    public BigDecimal calculateTotalCost() {
        BigDecimal total = super.calculateTotalCost();
        if (hasDrawers) {
            total = total.add(BigDecimal.valueOf(30));
        }
        return total;
    }

    @Override
    public String toString() {
        return "table: " + getName() + ", size: " + length + "x" + width + "x" + height + ", Drawers: " + hasDrawers;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Table)) return false;
        Table other = (Table) obj;
        return this.getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
    @Override
    public void assemble() {
        System.out.println("table assembled. " + getName());
    }
    @Override
    public void disassemble() {
        System.out.println("table disassembled. " + getName());
    }
}
