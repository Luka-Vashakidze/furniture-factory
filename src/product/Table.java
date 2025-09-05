package product;

import java.math.BigDecimal;

public class Table {

    private Furniture furniture;
    private int length;
    private int width;
    private int height;
    private boolean hasDrawers;

    public Table(Furniture furniture, int length, int width, int height, boolean hasDrawers) {
        this.furniture = furniture;
        this.length = length;
        this.width = width;
        this.height = height;
        this.hasDrawers = hasDrawers;
    }

    public Table() {
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
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

    public boolean isHasDrawers() {
        return hasDrawers;
    }

    public void setHasDrawers(boolean hasDrawers) {
        this.hasDrawers = hasDrawers;
    }

    public BigDecimal calculateTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;

        if (furniture != null) {
            total = furniture.calculateTotalCost();
        }

        if (hasDrawers) {
            total = total.add(BigDecimal.valueOf(30));
        }


        return total;
    }
}
